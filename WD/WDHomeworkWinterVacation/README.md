已实现接口：
/user/login
/user
/user/history?page=
未调试接口：
/user/history
/user/history/lc
/search
/search/download?text=

已完成任务：
爬取酷我音乐上20首歌曲并持久化
使用持久层框架对数据进行存储
登录注册功能
分页搜索，默认一页为10条记录

未完成任务：
登录后后端生成token并返回给前端，除登录注册外的所有接口都要对token进行检验，不合法或者过期按照要求返回结果
对登录和注册的值进行检验，在检验有误时按照要求返回结果
通过rid来下载音乐文件，对下载的音乐的历史记录进行保存
对历史记录进行收藏和删除
