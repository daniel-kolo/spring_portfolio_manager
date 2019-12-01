<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Stonks the investment manager</title>
    <style>
        input[type=text], select {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type=password], select {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type=submit] {
            width: 100%;
            background-color: #4F81BC;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color: #4F81BC;
        }

        div {
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
        }
    </style>
</head>
<body style=" background:#333">
<div style="background: none; padding-left: 20%; padding-right: 20%; align-content: center">
    <div style="max-width: 1200px">
        <h1>Register</h1>
        <p>Note: user name must be unique!</p>
        <form action="/registerUser" method="post">
            User name:
            <input type="text" name="userName"><br>
            Password:
            <input type="password" name="password">
            <br><br>
            <input type="submit" value="Submit">
        </form>
    </div>

</div>


</body>
</html>