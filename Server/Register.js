var express = require('express');
var router = express.Router();
var mysql = require('mysql');
var bodyParser = require('body-parser');
var connection = mysql.createConnection({
    host     : 'localhost',
    user     : 'root',
    password : 'zhaoxuan960518',
    database : 'anytime'
  });

  router.use(bodyParser.urlencoded({ extended: false }));  

// 该路由使用的中间件
router.use(function timeLog(req, res, next) {
  console.log('Time: ', Date.now());  //获取当前的操作时间  可以取消
  next();
});
// 定义网站主页的路由
router.get('/', function(req, res) {
  res.send('Register');
});
// 定义 about 页面的路由
router.post('/', function(req, res) {
        //connection.connect();
        var  addSql = 'INSERT INTO Users(UName,Upass,UName2) VALUES(?,?,?)'; 
        var UName = req.body.UName;
        var Upass = req.body.Upass;
        var UName2 = req.body.UName2;
        var  addSqlParams = [UName, Upass, UName2];  
        console.log('UName:', UName);  //搜索的ID
        console.log('Upass:', Upass);  //搜索的ID
        connection.query(addSql,addSqlParams,function (err, result) {
            if(err){
              console.log('[INSERT ERROR] - ',err.message);
              res.send([{"State":"-1"}]);
              return;
             }     
           res.send([{"State":"1"}]);
     });
});

module.exports = router;

