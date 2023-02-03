package com.test.service.impl;

import com.test.mapper.BookMapper;
import com.test.model.Book;
import com.test.service.IBookService;
import com.test.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author herixin
 * @create 2022-12-15 15:56
 */
@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> selectBookPage(Book book, PageBean pageBean) {
        return bookMapper.selectBookPage(book);
    }

    @Override
    public Book selectByPrimaryKey(Integer bookId) {
        return bookMapper.selectByPrimaryKey(bookId);
    }
}
