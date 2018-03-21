var express = require('express');
var router = express.Router();
var mysql = require('mysql');
var connection = mysql.createConnection({
    host     : 'localhost',
    user     : 'root',
    password : 'zhaoxuan960518',
    database : 'anytime'
  });

// 该路由使用的中间件
router.use(function timeLog(req, res, next) {
  console.log('Time: ', Date.now());  //获取当前的操作时间  可以取消
  next();
});
// 定义网站主页的路由
router.get('/', function(req, res) {
  res.send('Get USERS_Data');
});
// 定义 about 页面的路由
router.get('/:Name', function(req, res) {
    //connection.connect();
    var  sql = 'SELECT Upass FROM Users where UName = '+req.params.Name;
    console.log('UName:', req.params.Name);  //搜索的ID
    connection.query(sql,function (err, result) {
        if(err){
          console.log('[SELECT ERROR] - ',err.message);
          res.send(000);
          return;
        }
       console.log('--------------------------SELECT----------------------------');
       console.log(result);
       console.log('------------------------------------------------------------\n\n');  
      // connection.end();
       res.send(result);
 });
});

module.exports = router;

