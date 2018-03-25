var express = require('express');
var app = express();
var mysql = require('mysql');

var GetUsersPass = require('./Get_UsersPass');   //登录 用户的账号密码
var SaveUsersPass = require('./Register');   //登录 用户的账号密码
var GetAllTask = require('./Get_AllTask');    //获取所有没有被接收的任务
var ReleaseTask = require('./Release_Task');    //发布任务

var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : 'zhaoxuan960518',
  database : 'anytime'
});

connection.connect();  //一直开启连接

var server = app.listen(3000, function () {
  var host = server.address().address;
  var port = server.address().port;

  console.log('Example app listening at http://%s:%s', host, port);
});

app.use('/Users/get',GetUsersPass);
app.use('/Users/save',SaveUsersPass);
app.use('/AllTask/get',GetAllTask);
app.use('/ReleaseTask',ReleaseTask);
