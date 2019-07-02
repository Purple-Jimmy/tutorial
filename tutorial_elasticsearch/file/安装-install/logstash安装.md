# logstatsh安装
 
## rpm 方式

### 卸载旧版
1. 查看是否存在 
```
rpm -qa | grep -i logstash
```
2. 停止服务运行并删除 
```
rpm -e logstash-7.1.1-1.noarch --nodeps
```
3. 删除/etc下的配置文件 