var express = require('express');
var app = express();
var mysql = require('mysql');

var GetUsersPass = require('./Get_UsersPass');   //登录 用户的账号密码
var SaveUsersPass = require('./Register');   //登录 用户的账号密码

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
