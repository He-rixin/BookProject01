<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2022/10/10
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="r" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <link href="https://cdn.bootcdn.net/ajax/libs/layui/2.7.6/css/layui.css" rel="stylesheet"/>
    <script src="https://cdn.bootcdn.net/ajax/libs/layui/2.7.6/layui.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.1/jquery.js"></script>

</head>
<body>

<div class="layui-form layui-form-pane">
    <div class="layui-form-item">
        <div class="layui-input-inline">
            <input type="text" name="bookName" lay-verify="required" placeholder="请输入" autocomplete="off"
                   class="layui-input" id="bookName">
        </div>
        <button class="layui-btn" id="btn_search">
            <i class="layui-icon">&#xe615;</i> 搜索
        </button>
        <%--        <button data-method="offset" data-type="auto" class="layui-btn layui-btn-normal">居中弹出</button>--%>
        <button class="layui-btn" id="btn_add">
            <i class="layui-icon">&#xe608;</i> 添加
        </button>
    </div>
    <table id="bookList" lay-filter="test"></table>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    </fieldset>
</div>
<script type="text/html" id="bookEdit">
    <div class="layui-btn-group">
        <button class="layui-btn layui-btn-sm" lay-event="look">
            <i class="layui-icon">&#xe615;</i>
            查看
        </button>
        <button class="layui-btn layui-btn-sm" lay-event="edit">
            <i class="layui-icon">&#xe642;</i>
            修改
        </button>
        <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del">
            <i class="layui-icon">&#xe640;</i>
            删除
        </button>
    </div>
</script>
<script>

    var bookList, $, form, element;
    layui.use(['table', 'jquery', 'form', 'element'], function () {
        bookList = layui.table;
        $ = layui.jquery;
        form = layui.form;
        element = layui.element;
        queryData();

        $('#btn_search').click(function () {
            var name = $('#bookName').val();
            queryData(name);
        });
        $('#btn_add').click(function () {
            //弹窗方式
            // 先得到当前iframe层的索引
            // var index = parent.layer.getFrameIndex(window.name);
            // console.log(index)
            //再执行关闭
            // parent.layer.close(index);
            //调用父窗口中方法
            // parent.addTab('书本新增','addBook.jsp');
            //框架集
            parent.addTab("书本新增", 'addBook.jsp')
        });
        bookList.on('tool(test)', function (obj) {
            var row = obj.data;
            if (obj.event == 'del') {
                //删除
                console.log(row.bookId);
                delBook(row.bookId);
            } else if (obj.event == 'edit') {
                //修改编辑
                edit(row);
            } else if (obj.event == 'look') {
                look(row);
            }
        });


    });

    function look(row) {
        openMsg(row.bookId)
    }

    function edit(row) {

        parent.bookEdit('书本编辑', 'addBook.jsp', row);

    }

    function delBook(bookId) {
        $.ajax({
            url: 'deleteBook',
            data: {bookId},
            type: 'post',
            dataType: "json",
            success: function (data) {
                console.log(data);
                layer.msg(data.msg, {icon: data.code == 0 ? 6 : 5});
                queryData('');
            }

        });
    }

    // function initTable() {
    //     table.reload('bookList', {
    //         url: 'bookList',     //请求地址
    //         method: 'POST',                    //请求方式，GET或者POST
    //         loading: true,                     //是否显示加载条（默认 true）
    //         page: true,                        //是否分页
    //         where: {
    //             'bookName': bookName   //设定异步数据接口的额外参数，任意设
    //         },
    //         request: {                         //自定义分页请求参数名
    //             pageName: 'page', //页码的参数名称，默认：page
    //             limitName: 'rows' //每页数据量的参数名，默认：limit
    //         },
    //         //parseData数据格式解析的回调函数，用于将返回的任意数据格式解析成 table 组件规定的数据格式。
    //         parseData: function (res) { //res 即为原始返回的数据
    //             return {
    //                 "code": res.code == 200 ? 0 : res.code, //解析接口状态，返回结果的code值必须为0
    //                 "msg": res.msg,                         //解析提示文本
    //                 "count": res.count,                     //解析数据长度
    //                 "data": res.data                        //解析数据列表
    //             };
    //         },
    //         done: function (res, curr, count) {
    //         }
    //     });
    //
    // }

    function openMsg(bookId) {
        console.log(bookId)
        //先用书籍pid将相应的数据查询出来
        let    bookName;
        let   bookCategoryId;
        let    bookAuthor;
        let    bookPrice;
        let    bookImage;
        let   publishing;
        let   bookDesc;
        let   bookState;
        let   deployDatetime;
        let    salesVolume;
        $.ajax({
            async:false,//关闭异步
            url: 'http://localhost:8080/BookProject01/book/singleBook?bookId='+bookId,
            type: 'post',
            dataType: 'json',
            success: function (data) {
                console.log(data)
                bookName = data.data.bookName;
                bookImage ="https://ts2.cn.mm.bing.net/th?id=ORMS.0d8bac4983417db5d06d10d8fcfe10ae&pid=Wdp&w=300&h=156&qlt=90&c=1&rs=1&dpr=1.5&p=0";
                bookCategoryId = data.data.bookCategoryId;
                bookAuthor = data.data.bookAuthor;
                bookPrice = data.data.bookPrice;
                bookDesc = data.data.bookDesc;
                publishing = data.data.publishing;
                bookState = data.data.bookState;
                deployDatetime = data.data.deployDatetime;
                salesVolume = data.data.salesVolume;
                //从后端获取书籍对象，将对应的属性赋值给变量
                //     data.对象
            }
        })
        let content='<form class="layui-form layui-form-pane" lay-filter="myForm"><div class="layui-form-item"> <label class="layui-form-label">书籍编号：</label> <div class="layui-input-block"> <input type="text" name="goodsId" value="'+bookId+'" autocomplete="off"  lay-verify="required" class="layui-input"> </div> </div>' +
            ' <div class="layui-form-item"> <label class="layui-form-label">书籍图片：</label> <div class="layui-input-block"> <img style="width: 100px;height: 200px" src="'+bookImage+'"> </div> </div> ' +
            '<div class="layui-form-item"> <label class="layui-form-label">书籍名称：</label> <div class="layui-input-block"> <input type="text" name="goodsName" value="'+bookName+'" autocomplete="off" lay-verify="required"  class="layui-input"> </div> </div>' +
            ' <div class="layui-form-item"> <label class="layui-form-label">书籍简介：</label> <div class="layui-input-block"> <input type="text" name="goodsIntro" value="'+bookDesc+'" autocomplete="off" lay-verify="required" class="layui-input"> </div> </div> ' +
            ' <div class="layui-form-item"> <label class="layui-form-label">书籍价格：</label> <div class="layui-input-block"> <input type="text" name="goodsAuther" value="'+bookPrice+'" autocomplete="off" lay-verify="required" class="layui-input"> </div> </div>  '

        // content+='商品编号：<span>'+goodsId+'</span><br/>'
        // content+='图片：<img  src="'+img+'"></img><br/>'
        // content+='商品名称：<span>'+goodsName+'</span><br/>'
        // content+='商品提供者：<span>'+goodsAuthor+'</span><br/>'
        // content+='店铺名：<span>'+goodsShopName+'</span><br/>'
        // content+='商品价格：<span>'+goodsPrice+'</span><br/>'

        layer.open({
            type: 1
            ,offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
            ,id: 'layerDemo' //防止重复弹出
            ,content: content
            ,maxWidth: 400
            ,maxHeight:500
            ,btn: '确认'
            ,btnAlign: 'c' //按钮居中
            ,shade: 0 //不显示遮罩
            ,yes: function(){
                layer.closeAll();
            }
        });
    }

    function queryData(bookName) {
        bookList.render({
            elem: '#bookList'
            , height: 315
            , url: '../book/bookList' //数据接口
            , page: true//开启分页
            , where: {
                bookName: bookName
            }
            ,
            request: {
                //分页的参数等等
                pageName: 'page', //页码的参数名称，默认：page
                limitName: 'rows' //每页数据量的参数名，默认：limit
            }
            , cols: [[ //表头
                {field: 'bookId', title: 'ID', width: 60, sort: true, fixed: 'left'}
                , {field: 'bookName', title: '书名', width: 120}
                , {field: 'bookCategoryId', title: '类别', width: 60}
                , {field: 'bookAuthor', title: '作者', width: 80}
                , {field: 'bookPrice', title: '价格', width: 60, sort: true}
                , {field: 'publishing', title: '出版社', width: 150}
                , {field: 'bookDesc', title: '简介', width: 80, sort: true}
                , {field: 'bookState', title: '状态', width: 80, sort: true}
                , {field: 'deployDatetime', title: '出版时间', width: 135, sort: true}
                , {fixed: 'right', title: '操作', width: 180, align: 'center', toolbar: '#bookEdit'} //这里的toolbar值是模板元素的选择器
            ]]

        });
    }

</script>
</body>
</html>
