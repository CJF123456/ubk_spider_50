宏观数据同步系统 测试环境
1.确定好51的数据
2.找到QuartzTask 设置要跟新的数据 可直接执行main方法，可以执行定时
3.去51Tomcat下,pluginadmin下修改同步数据的定时
4.去16查看生成临时文件即可

正式环境
1.修改mybatis文件 链接10.0.0.16数据库，root 123456
2.去到 QuartzTask文件中
         new SendFileByftp().uploadFile("123.56.182.93", 21, "ftpuser",
			 "ftpuser", "/plugin/hgdata/", tempfile.getName(), input);
	上传数据到ftp
3.


