# set去重原理
set调用add()方法添加数据时,会调用hashSet的add()方法,hashSet的add()方法依赖hashMap的put方法.
如果要应用到复杂对象,则需要重写复杂对象的hashCode和equals方法