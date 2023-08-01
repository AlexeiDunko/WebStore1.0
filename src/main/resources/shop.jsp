<!DOCTYPE html>
<html xmlns:c="">
<head>
    <title>Shop</title>
</head>
<body>
<h1>Welcome, ${Alex}!</h1>
<form method="post" action="shop">
    <select name="item">
        <c:forEach var="item" items="${items}">
            <option value="${item.id}">${item.name} - ${item.price}</option>
        </c:forEach>
    </select>
    <input type="submit" name="action" value="Add Item" />
    <input type="submit" name="action" value="Submit" />
</form>
<h2>Your Cart:</h2>
<ul>
    <c:forEach var="item" items="${sessionScope.cart}">
        <li>${item.name} - ${item.price}</li>
    </c:forEach>
</ul>
</body>
</html>
