<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页</title>
</head>
<body>
<form>
    <table align="center">
        <tr>
            <td>账号</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="psd"></td>
        </tr>

        <tr>
            <td colspan="1" align="center">
                <input type="button" value="登录" onclick="login()">
            </td>
        </tr>
    </table>
</form>
</body>
<script src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">

    function login() {
        // 序列化表单内容
        var data = $("form").serialize();

        // 访问后端资源
        $.ajax({
            type: "post",
            url: "/user/login",
            data: data,
            success: function (resp) {
                // 将token存入localStorage
                localStorage.setItem("token", resp.token);

                // 跳转页面
                $(location).attr('href', '/success.jsp');
            }
        })
    }
</script>
</html>
