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
  res.send('Release_Task');
});
// 定义 about 页面的路由
router.post('/', function(req, res) {
        //connection.connect();
        var  addSql = 'INSERT INTO task(TaskId,PublisherUName,TaskState,TaskDetails) VALUES(?,?,?,?)'; 
        var TaskId = req.body.TaskId;
        var PublisherUName = req.body.PublisherUName;
        var TaskDetails = req.body.TaskDetails;
        var TaskState = "0";
        var  addSqlParams = [TaskId, PublisherUName, TaskState,TaskDetails];  
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

