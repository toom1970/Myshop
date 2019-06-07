package com.ddd.movie.utils;

import com.ddd.movie.pojo.Movie;
import com.ddd.movie.pojo.Photo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

public class JsonToObjectUtils {
    Gson gson = new Gson();

    public Movie JsonToMovie(String jsonString) {
        JsonObject object = gson.fromJson(jsonString, JsonObject.class);
        Movie movie = new Movie();
        try {
            movie.setName(object.get("nm").getAsString());
            movie.setId(object.get("id").getAsInt());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            movie.setReleaseDate(format.parse(object.get("frt").getAsString()));
            movie.setStarring(object.get("star").getAsString());
            movie.setType(object.get("cat").getAsString());
            movie.setLanguage(object.get("oriLang").getAsString());
            movie.setArea(object.get("fra").getAsString());
            movie.setLength(object.get("dur").getAsString());
            movie.setLanguage(object.get("oriLang").getAsString());
            movie.setOtherName(object.get("enm").getAsString());
            movie.setDirector(object.get("dir").getAsString());
            movie.setIntroduction(object.get("dra").getAsString());
            movie.setAlbumImg(object.get("albumImg").getAsString());
            if (movie.getPhotos() == null)
                movie.setPhotos(new HashSet<>());
            String[] photosStr = gson.fromJson(object.get("photos"), String[].class);
            for (String s : photosStr)
                movie.getPhotos().add(new Photo(s));
            return movie;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Movie> JsonToMovieList(String jsonString) {
        JsonArray jsonArray = gson.fromJson(jsonString, JsonArray.class);
        List<Movie> list = new ArrayList<>();
        for (Iterator it = jsonArray.iterator(); it.hasNext(); ) {
            list.add(JsonToMovie(it.next().toString()));
        }
        return list;
    }
}
