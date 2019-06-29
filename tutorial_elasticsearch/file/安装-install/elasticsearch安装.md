# elasticsearch 安装

## 卸载旧版
1. 查看是否存在 
```
rpm -qa | grep -i elasticsearch
```
2. 停止服务运行并删除 
```
rpm -e elasticsearch-6.3.0-1.noarch --nodeps
```
3. 删除/etc下的配置文件 


## rpm安装
1. 上传并解压,安装路径 /usr/share/elasticsearch,安装路径 /usr/share/elasticsearch 配置文件路径 /etc/elasticsearch/
```
rpm -ivh elasticsearch-6.3.0.rpm 
```
2. 启动服务
```
service elasticsearch start  
或者命令  
sudo chkconfig --add elasticsearch  
sudo -i service elasticsearch start  
sudo -i service elasticsearch stop   
sudo -i service elasticsearch status 
```
3. 测试是否安装成功 
```
curl http://localhost:9200/
```
4. 日志存放路径 /var/log/elasticsearch/



## tar安装







## 修改jvm



## 修改elasticsearch.yml
```
cluster.name: demon # 设置集群名称,开启了自发现功能后,ES会按照此集群名称进行集群发现
node.name: elk-1 #设置节点名称
bootstrap.memory_lock: false #配置内存禁用交换分区
bootstrap.system_call_filter: false
network.host: 0.0.0.0  # 设置 外网访问
discovery.zen.ping.unicast.hosts: ["0.0.0.0"] # 设置 集群节点发现列表
discovery.zen.minimum_master_nodes: 2   #集群可做master的最小节点数
http.cors.enabled: true   
http.cors.allow-origin: "*"
```



## 防火墙
1. 查看防火墙 systemctl status firewalld
2. 开启防火墙 systemctl start firewalld
3. 关闭防火墙 systemctl stop firewalld
4. 开启端口 firewall-cmd --zone=public --add-port=9200/tcp --permanent
5. 重启防火墙 firewall-cmd --reload
6. 查看端口是否开放 firewall-cmd --query-port=9200/tcp


