package kr.co.fortice.blog.controller;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PageList {
    INDEX_PAGE("index"),
    BLOG_MAIN_PAGE("blog_main"),
    BLOG_CREATE_PAGE("blog_create"),
    POST_CREATE_PAGE("post_write"),
    POST_READ_PAGE("post");

    private final String page;

    public String resource() {
        return page;
    }
}
