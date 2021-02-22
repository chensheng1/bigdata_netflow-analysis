#coding:utf-8
#!usr/bin/env python
'''
@author:lucas 1273085613@qq.com
@description:利用决策树模型识别设备为手持设备或非手持设备
@create date:2017/03/09

'''
import file_path
import read_file
import file_name
from sklearn import tree
import pandas as pd
import sys
import time
import pickle
import db_config
import datetime

def getYesterday(): 
	today=datetime.date.today() 
	oneday=datetime.timedelta(days=1) 
	yesterday=today-oneday  
	return yesterday

def get_file():
    file_path_all=''
    now_time=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
    file_index=int(now_time[11:13])
    if file_index==0:
        file_path_all=file_path.zip_log_file_path+getYesterday()+'/'+file_name.get_name_list('conn')[23]
    else:
        file_path_all=file_path.zip_log_file_path+now_time[0:10]+'/'+file_name.get_name_list('conn')[file_index-1]
    return file_path_all
def get_data():
    file_path_all=get_file()
    all_data=read_file.pandas_normal_gz(file_path_all)
    usefule_data=all_data.iloc[:-1,[8,9,10,17,19]]#duration,orig_bytes,orig_ip_bytes,resp_bytes,resp_ip_bytes
    usefule_data=usefule_data.dropna(how='any')
    pkl_file = open('device_tree_model.pkl', 'rb')
    device_tree_model=pickle.load(pkl_file)
    pkl_file.close()
    return list(device_tree_model.predict(usefule_data))

def write_database():
    result_data=get_data()
    yes_data_C=result_data.count('yes')
    no_data_C=result_data.count('no')
    #连接数据库并写入
    db=db_config.get_connected_db()
    cursor=db.cursor()
    time_data=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
    sql1 = "insert into device_c values('"+str(time_data)+"','"+str(no_data_C)+"','"+str(yes_data_C)+"')"
    try:
        # 执行sql语句
        cursor.execute(sql1)
        #results=cursor.fetchall()
        #print results
        # 提交到数据库执行
        db.commit()
    except:
        # Rollback in case there is any error
        db.rollback()
    
    # 关闭数据库连接
    db.close()

if __name__=='__main__':
    #plot_data()
    write_database()
    #print get_file()
    #print getYesterday()
