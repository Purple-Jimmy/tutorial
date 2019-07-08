# docker安装elk(mac)

## 安装elasticsearch
1. 查看es镜像列表
```
docker search elasticsearch
```
2. 下载镜像elasticsearch-kibana
```
docker pull elasticsearch-kibana

docker pull docker.elastic.co/elasticsearch/elasticsearch:6.3.2
```
3. 查看本地已安装的镜像
```
docker images
```
4. 运行
```
docker run -d -p 9200:9200 -p 9300:9300 -p 5601:5601 --name eskibana  nshou/elasticsearch-kibana

docker run -d --name es --net somenetwork  -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:6.3.2
```
5. 查看当前运行的容器
```
docker ps
```
6. 进入容器
```
docker exec -it 4d34fbf944a5 /bin/bash
```
7. 如果报错:stat /bin/bash: no such file or directory": unknown
```
sudo docker exec -it 2df4237ffe01 sh
```
8. 重启容器
```
docker restart es
```
9. 查看容器日志
```
docker logs 容器id
```
10. 查看容器ip地址
```
docker inspect 容器id
```

## 安装kibana
1. 运行

docker run -d -p 5601:5601 --link elasticsearch -e ELASTICSEARCH_URL=http://elasticsearch:9200 kibana
 
```
docker run -it -d -e ELASTICSEARCH_URL=http://127.0.0.1:9200 --name kibana --network=container:elasticsearch kibana
--network 指定容器共享elasticsearch容器的网络栈 (使用了--network 就不能使用-p 来暴露端口)


docker run -d -p 5601:5601 --name k3 3e581a516dcd
docker run -it -d -e ELASTICSEARCH_URL=http://127.0.0.1:9200 --name kibana --network=container:elasticsearch 3e581a516dcd
docker run -d --network mynetwork -e ELASTICSEARCH_URL=http://elasticsearch_2_4:9200 -p 5601:5601 kibana:4.6
```
2. 进入容器修改配置文件
```
docker exec -it kibana /bin/bash
添加
elasticsearch.ssl.verify: false
```