<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2022/12/15
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="r" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>后台管理</title>

    <link href="https://cdn.bootcdn.net/ajax/libs/layui/2.7.6/css/layui.css" rel="stylesheet"/>
    <script src="https://cdn.bootcdn.net/ajax/libs/layui/2.7.6/layui.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.1/jquery.js"></script>
    <style>
        .layui-tab-title > li:first-child > i {
            display: none;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">layui 后台布局</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <%--                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">--%>
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">所有商品</a>
                    <dl id="module" class="layui-nav-child">

                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <div class="layui-tab" lay-filter="demo" lay-allowclose="true">
            <ul class="layui-tab-title">
                <li class="layui-this" lay-id="11">首页</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">首页111</div>

            </div>
        </div>
    </div>
    <%--<script src="../src/layui.js"></script>--%>
    <script>
    </script>
</body>


<script>
    var layer, $, element, row;
    //JavaScript代码区域
    layui.use(['element', 'layer', 'jquery'], function () {
        element = layui.element;
        $ = layui.jquery;
        var module = $("#module");
        $.ajax({
            url: "../permission/menuList",
            type: "post",
            dataType: "json",
            success: function (data) {
                var ll = "";
                $.each(data.data, function (index, item) {
                    var selected = 'layui-nav-itemed'
                    var li = '<li class="layui-nav-item ' + selected + '"><a class="" href="javascript:;">' + item.pername + '</a>';
                    li += '<dl class="layui-nav-child">';
                    //循环创建子节点
                    $.each(item.list, function (a, b) {
                        // console.log(b)
                        li += '<dd><a href="javascript:addTab(\'' + b.pername + '\',\'' + b.url + '\');">' + b.pername + '</a></dd>';
                        $.each(b.list, function (c, d) {
                            // console.log(d)
                            li += '<dd><a href="javascript:addTab(\'' + d.pername + '\',\'' + d.url + '\');">' + d.pername + '</a></dd>';
                        })
                    })
                    li += "</dl>"
                    ll += li;
                })
                ll += "</li>"
                // console.log(ll)
                module.append(ll);
            },

        });
    });

    function addTab(title, url) {
        if ($(".layui-tab-title li[lay-id='" + title + "']").length > 0) {
            element.tabChange('demo', title);

        } else {
            element.tabAdd('demo', {
                title: title,
                content: '<iframe frameborder="0" src="' + url + '" scrolling="auto" style="width:100%;height:100%"></iframe>',
                id: title,
            });
            element.tabChange('demo', title);
        }

    }

    function bookEdit(title, url, row2) {
        row = row2;
        console.log($(".layui-tab-title li[lay-id='" + title + "']").length)
        if ($(".layui-tab-title li[lay-id='" + title + "']").length > 0) {
            element.tabChange('demo', title);
        } else {
            element.tabAdd('demo', {
                title: title,
                content: '<iframe frameborder="0" src="' + url + '" scrolling="auto" style="width:100%;height:100%"></iframe>',
                id: title,
            });
            element.tabChange('demo', title);
        }

    }
</script>

</html>