package com.test.service;

import com.test.model.Book;
import com.test.util.PageBean;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author herixin
 * @create 2022-12-15 15:54
 */
public interface IBookService {
    @Cacheable("BookList")
    List<Book> selectBookPage(Book book, PageBean pageBean);
    @Cacheable("SingleBook")

    Book selectByPrimaryKey(Integer bookId);


}
