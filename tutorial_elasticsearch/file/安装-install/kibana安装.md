# kibana 安装

1. 查看是否存在 
```
rpm -qa | grep -i kibana
```
2. 停止服务运行并删除 
```
rpm -e kibana-6.4.3-1.x86_64 --nodeps
```
3. 删除/etc下的配置文件 

4. 查看版本号
```
rpm -qa | grep -i kibana
```

## rpm安装
1. 上传rpm包到路径/usr/search
2. 解压 rpm -ivh kibana-6.1.1-x86_64.rpm 安装路径在/usr/share/kibana
3. 修改yml文件 server.host: "0.0.0.0"，可以远程访问,yml路径在/etc/kibana
4. 命令
```
sudo chkconfig --add kibana # 设置自动启动
sudo -i service kibana start
sudo -i service kibana stop
sudo -i service kibana restart
或者
sudo /bin/systemctl daemon-reload
nohup ./bin/kibana & 后台启动
```
5. 日志目录 /var/log/kibana


## tar安装
1. 下载 wget https://artifacts.elastic.co/downloads/kibana/kibana-7.2.0-linux-x86_64.tar.gz
2. 解压 tar -xzf kibana-7.2.0-linux-x86_64.tar.gz
3. 启动
```
cd kibana-7.2.0-linux-x86_64/ 
./bin/kibana
```

## 修改配置
编辑kibana.yml文件
```
server.host: "0.0.0.0"   设置远程访问 
```




## 权限问题
1. 新增用户 adduser name
2. 设置密码 passwd name
3. 设置权限 chown -R name /home/elasticsearch



