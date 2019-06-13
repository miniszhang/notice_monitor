# notice_monitor
apicloud通知栏消息抓取模块(仅支持安卓)

该模块可以抓取安卓端其它APP的消息通知栏内容，例如支付宝，微信等

使用方法。请将目录里面的zip包上传到你的apicloud项目模块里面。在你的apicloud程序apiready方法调用即可调用，如下:

<body>
  
  <div class="main">
  
    <h1>获取的内容将在以下展示:</h1>
    
    <div class="content"></div>
  
  </div>

</body>

<script type="text/javascript">
  
    apiready = function() {
        
        var getNoticeMsg = api.require('notice');
        
        getNoticeMsg.notice(function(ret){
        
				
            var r = document.getElementsByClassName("content")[0];
            
            var str = JSON.parse(ret['res']);
            
            if (str['type'] == 'add') {
              
              var c = "  <b>标题:</b>"+ str['title'] + "  <b>ID号:</b>"+ str['id'] + "  <b>包名:</b>"+ str['packgename'] +" <b> 时间:</b>"+ str['time'] +"  <b>内容:</b>"+ str['content'];
              
              r.innerHTML += "<br><span>"+ c +"</span>";
            
            }
        
        });
    
    };

</script>
</html>

#返回值
模块返回数据类型是json字符串字段，总共有6个键值:
type(监听类型，当值为“add”表示通知栏有新的消息，当值为"remove"表示通知栏有消息移)
id(对应的通知栏ID，可不用关心)
title(通知栏消息标题)
packgename（所对应的APP包名，例如支付宝的包名是com.eg.android.AlipayGphone，微信包名com.tencent.mm）
time(获取到通知栏消息里面的时间)
content（通知栏消息里面的具体内容）

