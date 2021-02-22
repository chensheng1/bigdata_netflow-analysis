#coding:utf-8
#!usr/bin/env python
'''
@author:leilf 
@description:app ranking
@create date:2017/05/24
'''

'''
step:
	1.根据时长
'''
from  fileTraff import file_path
from  fileTraff import read_file
from  fileTraff import file_name
from  DBTraff import db_base
from  DBTraff import db_config
import pandas as pd
import sys
import time
import datetime
import os

def get_data_base_port():
	'''
	获取数据库中收录的APP
	'''
	sql="select port1,name from application_port"
	sql_result=db_base.execute_result(sql)
	result={}
	for i in sql_result:
		result[i[0]]=i[1]
	return result
	
def getYesterday():
	'''
	获取昨天日期
	'''
	today=datetime.date.today()
	oneday=datetime.timedelta(days=1)
	yesterday=today-oneday
	return yesterday
	
def get_time_data():
	'''
	获取插入的时间数据
	'''
	time_data=''
	now_time=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
	file_index=int(now_time[11:13])#获取小时值
	if file_index==0:
		time_data=str(getYesterday())
	else:
		time_data=now_time[0:10]
	return time_data#年月日时
	
def get_past_hour_ts(n_hour):
	'''
	获取过去n_hour个小时整点时间戳：if log日志中存在大于整点时间戳则置为整点时间戳。
	'''
	now_time=time.strftime('%Y-%m-%d %H',time.localtime(time.time()))
	ts=time.mktime(time.strptime(now_time,'%Y-%m-%d %H'))-60*n_hour
	return ts
	
def get_file(filename):
	'''
	获取所需读取文件的绝对路径
	按小时读取.log的压缩文件
	'''
	file_path_all=''
	now_time=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
	file_index=int(now_time[11:13])
	if file_index==0:
		file_path_all=file_path.zip_log_file_path+getYesterday()+'/'+file_name.get_name_list(filename)[23]
	else:
		file_path_all=file_path.zip_log_file_path+now_time[0:10]+'/'+file_name.get_name_list(filename)[file_index-1]
	return file_path_all	

def get_logdata_df():
	'''
	获取所需写入数据库的数据
	返回dataframe,”用户，app，duration“
	'''
	file_path_all=get_file('conn')
	#file_path_all='test_file/conn.gz'
	#文件不存在直接return
	if not os.path.exists(file_path_all):
		return pd.DataFrame()
	#读取文件	
	df=read_file.pandas_normal_gz(file_path_all)
	df=df.iloc[:-1,[0,2,5]]#ts,origIP,resp_port
	df=df.dropna(how='any')
	grouped=df.groupby([2,5])#
	
	db_port=get_data_base_port()#数据库中收录的app,
	#对组开始处理
	
	np_list=[]
	for gp in grouped.groups: 
		data_port=gp[1]
		if data_port in db_port.iterkeys():
			gp_df=grouped.get_group(gp)#
			
			
			min_ts=gp_df.iloc[:,0].min()
			if min_ts<get_past_hour_ts(1):
				min_ts=get_past_hour_ts(1) 				
			max_ts=gp_df.iloc[:,0].max()
			
			duration=round(max_ts-min_ts,3)
			np_list.append([gp[0],gp[1],duration]) 
	df_result=pd.DataFrame(np_list,columns=['user','loveapp','duration'])
	return df_resultd
	#print df_result




def get_database_df(date_time):
	'''
	获取date_time当天数据库中的数据
	返回dataframe,“用户，host，duration”
	'''
	sql="select user,loveapp,duration from user_app where date='%s'"%(date_time)
	sql_result=db_base.execute_result(sql)
	np_list=[]
	for i in sql_result:
		np_list.append([i[0],i[1],i[2]])
	df_result=pd.DataFrame(np_list,columns=['user', 'loveapp','duration'])
	return df_result

def write_database():
	'''
	1.获取要当天数据库存在的数据df；
	2.获取从log中读取的df；
	3.合并df，并计算值得到结果；
	4.删除数据库当天的数据；
	5.写入结果；
	'''
	time_data=get_time_data()
	#1.获取要当天数据库存在的数据df；
	database_df=get_database_df(time_data)
	#2.获取从log中读取的df；
	logdata_df=get_logdata_df()
	if len(logdata_df)==0:
		return
	#3.合并df，并计算值得到结果；
	concat_df_group=pd.concat([logdata_df,database_df,database_df],ignore_index=True).groupby(['user','loveapp'])
	#4.删除数据库当天的数据；
	sql="delete from user_app where date='%s'"%(time_data)
	db_base.execute_no_result(sql)
	#5.写入结果；
	sql1="insert into user_app(date,user,loveapp,duration) values"
	for gp in concat_df_group.groups:
		_user=gp[0]
		_loveapp=gp[1]
		_duration=concat_df_group.get_group(gp).duration.sum()
		sql1=sql1+"('%s','%s','%s',%d)," %(time_data,_user,_loveapp,_duration)
	sql1=sql1[:-1]
	db_base.execute_no_result(sql1)#批量插入



def main():
	write_database()
	
if __name__=='__main__':
	main()

