#!usr/bin/env python
#coding:cp936
'''
@version:$1.0$

@author: Shi Zhikai

@copyright:hubu1411

Create Date:2016-11-21

全局变量信息模块
'''

#conn.log文件的全部字段
bro_conn_field=['ts','uid','id.orig_h','id.orig_p','id.resp_h','id.resp_p','proto','service',
'duration','orig_bytes','resp_bytes','conn_state','local_orig','local_resp','missed_bytes',
'history','orig_pkts','orig_ip_bytes','resp_pkts','resp_ip_bytes','tunnel_parents']

#http.log文件的全部字段
bro_http_field=['ts','uid','id.orig_h','id.orig_p','id.resp_h','id.resp_p','trans_depth','method',
'host','uri','referrer','user_agent','request_body_len','response_body_len','status_code',
'status_msg','info_code','info_msg','filename','tags','username','password','proxied',
'orig_fuids','orig_mime_types','resp_fuids','resp_mime_types']

device_key=['Android','iPad','iPhone','ARCHOS','BlackBerry',
'CUPCAKE','FacebookTouch','iPod','Kindle','LG','Links','Linux armv6l',
'Linux armv7l','Maemo',' Minimo','Mobile Safari','Nokia','Opera Mini',
'Opera Mobi','PalmSource','PlayStation','SAMSUNG','Symbian',' SymbOS',
'webOS','Windows CE','Windows Mobile','Zaurus','Windows NT ','Windows 7',
'Windows Vista','Windows XP','Windows Server','IntelMac OS X',
'PPC Mac OS X','MacBook','iMac','Fedora','Ubuntu','Gentoo','SUSE',
'Linux x86 64','Linux i686','Wii Connect']
