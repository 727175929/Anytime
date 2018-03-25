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
  res.send('Get AllTask');
});
// 定义 about 页面的路由
router.get('/All', function(req, res) {
    //connection.connect();
    var  sql = 'SELECT * FROM task where TaskState = 0';
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

// router.get('/:Person', function(req, res) {
//     //connection.connect();
//     var  sql = 'SELECT * FROM task where PublisherUname = '+req.params.Person+' or ReceiveUname = '+req.params.Person;
//     connection.query(sql,function (err, result) {
//         if(err){
//           console.log('[SELECT ERROR] - ',err.message);
//           res.send(000);
//           return;
//         }
//        console.log('--------------------------SELECT----------------------------');
//        console.log(result);
//        console.log('------------------------------------------------------------\n\n');  
//       // connection.end();
//        res.send(result);
//  });
// });

router.get('/:id', function(req, res) {
  //connection.connect();
  var modSql = "UPDATE task set TaskState = 2 where TaskId = ?"
  var modSqlParams = [req.params.id]
  connection.query(modSql,modSqlParams,function (err, result) {
    if(err){
          console.log('[UPDATE ERROR] - ',err.message);
          return;
    }        
   console.log('--------------------------UPDATE----------------------------');
   console.log('UPDATE affectedRows',result.affectedRows);
   console.log('-----------------------------------------------------------------\n\n');
  });
  var  sql = 'SELECT * FROM task where TaskState = 0'
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

