<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<h1 align="center" style="color: blue">欢迎<span id="username"></span>来到主页</h1>
</body>
<script src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
    $(function () {
        // 从localStorage中获取token
        var token = localStorage.getItem("token");

        // 如果token为空
        if (token === null) {
            // 跳转回登录页面
            $(location).attr('href', '/index.jsp');

        }
            // 访问后端接口获取资源
            $.ajax({
                type: "post",
                url: "/user/get",
                data: {"token": token},
                success: function (resp) {

                    // 如果响应状态为200
                    if (resp.code === 200) {
                        // 获取资源
                        $("#username").text(resp.data.name)
                    }
                }
            })
    })
</script>
</html>
