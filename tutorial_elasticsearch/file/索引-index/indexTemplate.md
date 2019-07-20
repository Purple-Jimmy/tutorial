# indexTemplate
模板只有在索引新创建时才会产生作用,修改模板不会影响已创建的索引

## 工作方式
当索引新创建时
1. 应用elasticsearch默认的settings和mappings
2. 应用order数值低的template中的设定
3. 应用order数值高的template中的设定,并覆盖之前的设定
4. 应用创建索引时用户指定的settings和mappings,并覆盖之前的设定


## template api

### 查看指定名字的template
GET /_template/template_name

### 查看匹配前缀的template
GET /_template/temp*






## dynamic template