package pl.springkurs.shop.commons.web;

import lombok.Data;

import java.util.List;

@Data
public class ResultPageDto<T> {

    List<T> data;
    int pageNumber;
    long pageSize;
}
