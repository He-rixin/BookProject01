package com.test.controller;

import com.test.model.Book;
import com.test.service.IBookService;
import com.test.util.JsonResponseBody;
import com.test.util.JsonResponseStatus;
import com.test.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author herixin
 * @create 2022-12-15 16:46
 */
@Controller
@RequestMapping("/book")
@CrossOrigin
public class BookController {
    @Autowired
    private IBookService iBookService;

    @ResponseBody
    @RequestMapping("/bookList")
    public Map<String, Object> bookList(Book book, PageBean pageBean) {
        System.out.println("pageBean = " + pageBean.getPage() + "," + pageBean.getRows());
        List<Book> books = iBookService.selectBookPage(book, pageBean);
        Map<String, Object> map = new HashMap<>();
        if (books == null || books.size() <= 0) {
            map.put("code", JsonResponseStatus.ERROR.getCode());
            map.put("msg", JsonResponseStatus.ERROR.getMsg());
            map.put("count", 0);
            map.put("data", null);
            return map;
        }
        List<Book> books1 = iBookService.selectBookPage(book, null);

        map.put("code", 0);
        map.put("msg", JsonResponseStatus.SUCCESS.getMsg());
        map.put("count", books1.size());
        map.put("data", books);
        return map;
    }

    @ResponseBody
    @RequestMapping("/adminBookList")
    public JsonResponseBody<List<Book>> adminBookList(Book book, PageBean pageBean) {
        List<Book> books = iBookService.selectBookPage(book, pageBean);
        JsonResponseBody<List<Book>> jrb = null;
        if (books == null || books.size() <= 0) {
            jrb = new JsonResponseBody<>(JsonResponseStatus.ERROR.getCode(), JsonResponseStatus.ERROR.getMsg());
        } else {
            List<Book> books1 = iBookService.selectBookPage(book, null);
            jrb = new JsonResponseBody<>(JsonResponseStatus.SUCCESS.getCode(), JsonResponseStatus.SUCCESS.getMsg(), books, (long) books1.size());
        }

        return jrb;
    }

    @RequestMapping("/toBookList")
    public String toBookList() {
        return "bookcrud/bookList";
    }

    @RequestMapping("/singleBook")
    @ResponseBody
    public Map<String, Object> singleBook(Integer bookId) {
        System.out.println("bookId = " + bookId);
        Book book = iBookService.selectByPrimaryKey(bookId);
        Map<String, Object> map = new HashMap<>();
        if (book == null) {
            map.put("code", 1);
            map.put("msg", "查询失败");
            map.put("count", 0);
            map.put("data", null);
            return map;
        }
        map.put("code", 0);
        map.put("msg", "查询成功");
        map.put("count", 1);
        map.put("data", book);
        return map;
    }
}
