package com.ddd.movie.service.serviceImpl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class RestPageImpl<T> extends PageImpl<T>{

    private static final long serialVersionUID = 3248189030448292002L;

    public RestPageImpl(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
        // TODO Auto-generated constructor stub
    }

    public RestPageImpl(List<T> content) {
        super(content);
        // TODO Auto-generated constructor stub
    }

    /* PageImpl does not have an empty constructor and this was causing an issue for RestTemplate to cast the Rest API response
     * back to Page.
     */
    public RestPageImpl() {
        super(new ArrayList<T>());
    }

}