#coding:utf-8
#!usr/bin/env python
'''
@author:lucas 1273085613@qq.com
@description:利用决策树模型识别设备为手持设备或非手持设备
@create date:2017/03/09

'''
from my_modules import file_path
from my_modules import read_file
from sklearn import tree
import pandas as pd
import sys
import time
import pickle
import db_config
def train_model():
    '''
    通过处理的数据，训练得到模型并保存
    '''
    #获取训练数据
    all_data=read_file.pandas_csv('trainSet07022.csv')
    #输入向量
    in_data=all_data.iloc[:,[0,1,2,3,4]]
    #正确的输出向量
    out_data=all_data.iloc[:,5]
    #获取scikit-learn决策树分类器
    clf=tree.DecisionTreeClassifier()
    #训练决策树模型
    clf=clf.fit(in_data,out_data)
    #保存训练模型
    #joblib.dump(clf,'device_tree_model.pkl')
    #device_tree_model=joblib.load('device_tree_model.pkl')
    #print device_tree_model.predict([0.087201,397,657,333,465])
    output = open('device_tree_model.pkl', 'wb')
    pickle.dump(clf,output)
    output.close()
if __name__=='__main__':
    #plot_data()
    train_model()

