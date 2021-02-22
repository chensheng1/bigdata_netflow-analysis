#coding:utf-8
#!usr/bin/env python
'''
@author:lucas 1273085613@qq.com
@description:host排名统计
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

def get_file(filename):
    file_path_all=file_path.current_log_file_path+filename
    return file_path_all
def get_data():
    '''
    获取所需有效日志文件数据
    @return ：dataframe
    '''
    #调取read_file模块函数读取文件
    conn_log=get_file('conn.log')
    http_log=get_file('http.log')
    df_conn=read_file.pandas_normal(conn_log)
    df_http=read_file.pandas_normal(http_log)
    #获取所需要的信息
    df_conn_useful=df_conn.iloc[:-1,[1,17,19]]#uid,orig_ip_bytes,resp_ip_bytes
    df_http_useful=df_http.iloc[:-1,[1,8]]#uid,host
    df_all=pd.merge(df_conn_useful,df_http_useful,on=1)#uid,orig_ip_bytes,resp_ip_bytes,host
    return df_all.dropna(how='any')
    
def clear_table():
    #连接数据库并写入
    db=db_config.get_connected_db()
    cursor=db.cursor()
    sql0="delete from temp_host"
    try:
        # 执行sql语句
        cursor.execute(sql0)
        #results=cursor.fetchall()
        #print results
        # 提交到数据库执行
        db.commit()
    except:
        # Rollback in case there is any error
        db.rollback()
    
    # 关闭数据库连接
    db.close()
    
def write_database():
    '''
    写数据
    '''
    df_all=get_data()
    df_all['all_bytes']=df_all[17]+df_all[19]
    df_useful=df_all.iloc[:,[3,4]].copy()#host,all_bytes
    df_useful.rename(columns={8:'host'},inplace = True)
    all_bytes=df_useful['all_bytes'].sum()#所有hosts总流量
    df_plot=df_useful.groupby('host').sum().sort_values(by='all_bytes',ascending=False).head(10)
    top_host=list(df_plot.index)
    labels=top_host#top 10 host 列表
    top_host_bytes=list(df_plot['all_bytes'])
    sizes=top_host_bytes #top 10 host 列表对应流量大小
    
    #连接数据库并写入
    clear_table()
    for i in range(len(labels)):
        value1=labels[i]
        value2=str(sizes[i])
        db=db_config.get_connected_db()
        cursor=db.cursor()        
        sql1 = "insert into temp_host values('"+value1+"','"+value2+"')"
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
    #write_database()
    print get_file()
    #print getYesterday()
