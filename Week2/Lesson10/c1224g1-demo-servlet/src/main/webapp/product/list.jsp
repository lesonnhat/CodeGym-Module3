<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        /* http://meyerweb.com/eric/tools/css/reset/
    v2.0 | 20110126
    License: none (public domain)
    */
        html, body, div, span, applet, object, iframe,
        h1, h2, h3, h4, h5, h6, p, blockquote, pre,
        a, abbr, acronym, address, big, cite, code,
        del, dfn, em, img, ins, kbd, q, s, samp,
        small, strike, strong, sub, sup, tt, var,
        b, u, i, center,
        dl, dt, dd, ol, ul, li,
        fieldset, form, label, legend,
        table, caption, tbody, tfoot, thead, tr, th, td,
        article, aside, canvas, details, embed,
        figure, figcaption, footer, header, hgroup,
        menu, nav, output, ruby, section, summary,
        time, mark, audio, video {
            margin: 0;
            padding: 0;
            border: 0;
            font-size: 100%;
            font: inherit;
            vertical-align: baseline;
        }

        /* HTML5 display-role reset for older browsers */
        article, aside, details, figcaption, figure,
        footer, header, hgroup, menu, nav, section {
            display: block;
        }

        body {
            line-height: 1;
        }

        ol, ul {
            list-style: none;
        }

        blockquote, q {
            quotes: none;
        }

        blockquote:before, blockquote:after,
        q:before, q:after {
            content: '';
            content: none;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
        }

        /* Noi dung chinh*/
        #header {
            background-color: darkgreen;
            width: 100%;
            height: 150px;
        }

        #content {
            background-color: chocolate;
            width: 70%;
            height: 500px;
            float: left;
            opacity: 50%;
        }

        #sidebar {
            background-color: chartreuse;
            width: 30%;
            height: 500px;
            float: left;
            opacity: 50%;
        }

        #footer {
            background-color: brown;
            width: 100%;
            height: 150px;
            clear: both;
        }

        .product {
            background-color: aqua;
            width: 200px;
            height: 200px;
            margin: 20px;
            float: left;
        }

        h1 {
            color: #ffffff;
            font-size: 24px;
        }

        #nav {
            background-color: red;
            width: 100%;
            height: 50px;
            position: fixed;
            top: 0px;
            left: 0px;
            z-index: 1000;
        }

        @media only screen and (max-width: 600px) {
            .product {
                width: 120px;
                height: 120px;
            }
        }
    </style>

</head>
<body>
<div id="header">
    <h1>Header</h1>
</div>
<div id="nav"></div>
<div id="content">
    <h1>
        Content
    </h1>
    <c:forEach var="p" items="${listProducts}">
        <div class="product">
            <p><c:out value="${p.getName()}"/></p>
            <p><c:out value="${p.getPrice()}"/></p>
        </div>
    </c:forEach>

</div>
<div id="sidebar">
    <h1>Sidebar</h1>
</div>
<div id="footer">
    <h1>Footer</h1>
</div>
</body>
</html>