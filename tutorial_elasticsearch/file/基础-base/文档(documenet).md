# 概念

## document 文档
1. elasticsearch是面向文档的,文档是所有可搜索数据的最小单位
2. 文档会被序列化成json格式保存在elasticsearch中


## 文档元数据
标注文档的相关信息
1. _index
2. _type
3. _id:文档的唯一id
4. _source:文档的原始json数据
5. _all:整合所有字段内容到该字段,已废除
6. _version:文档版本信息
7. _score:相关性打分