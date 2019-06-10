package com.ddd.movie.service.serviceImpl;

import com.ddd.movie.dao.CinemaDao;
import com.ddd.movie.mapper.CinemaMapper;
import com.ddd.movie.pojo.Cinema;
import com.ddd.movie.pojo.Movie;
import com.ddd.movie.pojo.ReleaseInfo;
import com.ddd.movie.service.CinemaService;
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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cinemaService")
public class CinemaServiceImpl implements CinemaService {
    @Resource(name = "cinemaDao")
    CinemaDao cinemaDao;
    @Resource(name = "cinemaMapper")
    CinemaMapper cinemaMapper;

    @Override
    public PageInfo findPageByMybatis(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Cinema> pagelist = cinemaMapper.findAll();
        return new PageInfo<>(pagelist);
    }

    @Override
    public Cinema findById(int id) {
        return cinemaMapper.findCinemaById(id);
    }

    @Override
    public Cinema findByIdJson(int id) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url = "http://127.0.0.1:5000/cinemaDetail?cinemaId=" + id;
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity responseEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(responseEntity);
            return new JsonToObjectUtils().JsonToCinema(response);
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
    public List<Movie> findReleaseMovie(int id) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url = "http://127.0.0.1:5000/cinemaDetail?cinemaId=" + id;
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity responseEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(responseEntity);
            return new JsonToObjectUtils().JsonToReleaseMovieList(response);
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
    public List<ReleaseInfo> findReleaseInfo(int id) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url = "http://127.0.0.1:5000/cinemaDetail?cinemaId=" + id;
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity responseEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(responseEntity);
            return new JsonToObjectUtils().JsonToReleaseInfoList(response);
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
    public Cinema findByName(String name) {
        return cinemaDao.findByName(name);
    }

    @Override
    public List<Cinema> findAll(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity responseEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(responseEntity);
            return new JsonToObjectUtils().JsonToCinemaList(response);
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

    public int cinemaPageNum(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity responseEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(responseEntity);
            int n = new Gson().fromJson(response, JsonObject.class).get("paging").getAsJsonObject().get("total").getAsInt();
            return n / 20;
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
    public int add(Cinema cinema) {
        Cinema saved = cinemaDao.saveAndFlush(cinema);
        return saved.getId();
    }

    @Override
    public int delete(Cinema cinema) {
        cinemaDao.delete(cinema);
        return 0;
    }

    @Override
    public int update(Cinema cinema) {
        Cinema saved = cinemaDao.saveAndFlush(cinema);
        return saved.getId();
    }
}
