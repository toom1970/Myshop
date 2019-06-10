package com.ddd.movie.service.serviceImpl;

import com.ddd.movie.dao.MovieDao;
import com.ddd.movie.dao.PhotoDao;
import com.ddd.movie.dao.TagDao;
import com.ddd.movie.mapper.MovieMapper;
import com.ddd.movie.pojo.Movie;
import com.ddd.movie.pojo.Photo;
import com.ddd.movie.pojo.Tag;
import com.ddd.movie.service.MovieService;
import com.ddd.movie.utils.JsonToObjectUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("movieService")
@CacheConfig(cacheNames = "movie")
public class MovieServiceImpl implements MovieService {
    @Resource(name = "movieDao")
    MovieDao movieDao;
    @Resource(name = "movieMapper")
    MovieMapper movieMapper;
    @Autowired
    PhotoDao photoDao;
    @Autowired
    TagDao tagDao;
    Gson gson = new Gson();

    @Override
    @Cacheable(key = "'page'+#page")
    public PageInfo findPageByMybatis(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Movie> pagelist = movieMapper.findAll();
        return new PageInfo<>(pagelist);
    }

    @Override
//    @Cacheable(key = "'all'")
    public List<Movie> findAll(int page) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url = "http://127.0.0.1:5000/movieOnInfoList?offset=" + 12 * page;
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity responseEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(responseEntity);
            return new JsonToObjectUtils().JsonToMovieList(response,page);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpResponse != null)
                    httpResponse.close();
                if (httpClient != null)
                    httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int MoviePageNum(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity responseEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(responseEntity);
            int n = new Gson().fromJson(response, JsonObject.class).get("total").getAsInt();
            return n / 12;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpResponse != null)
                    httpResponse.close();
                if (httpClient != null)
                    httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    @Override
    @Cacheable(key = "#id")
    public Movie findById(int id) {
        return movieMapper.findMovieById(id);
    }

    @Override
//    @Cacheable(key = "#id")
    public Movie findByIdJson(int id) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url = "http://127.0.0.1:5000/movieDetail?movieId=" + id;
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity responseEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(responseEntity);
            Movie movie = new JsonToObjectUtils().JsonToMovie(response);
            return movie;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpResponse != null)
                    httpResponse.close();
                if (httpClient != null)
                    httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    public Movie findByName(String name) {
        return movieMapper.findMovieByName(name);
    }

    @Override
    @CacheEvict(allEntries = true)
    public int add(Movie movie) {
        Movie saved = movieDao.saveAndFlush(movie);
        if (movie.getPhotos() != null) {
            for (Photo p : movie.getPhotos())
                photoDao.save(p);
        }
        if (movie.getTags() != null) {
            for (Tag t : movie.getTags())
                tagDao.save(t);
        }
        return saved.getId();
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(key = "#movie.getId()"),
//            @CacheEvict(key = "#movie.getName()"),
//            @CacheEvict(key = "'all'")})
    @CacheEvict(allEntries = true)
    public int delete(Movie movie) {
        movieDao.delete(movie);
        return 0;
    }

    @Override
    @CacheEvict(allEntries = true)
    public int update(Movie movie) {
        Movie saved = movieDao.saveAndFlush(movie);
        return saved.getId();
    }

    @Override
    public Page<Movie> findPageByJpa(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size)
            size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
        Page<Movie> pages = movieDao.findAll(pageable);
        return pages;
    }
}
