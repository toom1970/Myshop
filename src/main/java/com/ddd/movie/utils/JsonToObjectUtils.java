package com.ddd.movie.utils;

import com.ddd.movie.pojo.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

public class JsonToObjectUtils {
    Gson gson = new Gson();

    public Movie JsonToMovie(String jsonString) {
        JsonObject object = gson.fromJson(jsonString, JsonObject.class);
        object = gson.fromJson(object.get("detailMovie").toString(), JsonObject.class);
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
            movie.setAlbumImg(object.get("img").getAsString());
            double score = object.get("distributions").getAsJsonArray().get(0).getAsJsonObject().get("proportion").getAsDouble() / 10;
            movie.setScore((float) score);
            if (movie.getPhotos() == null)
                movie.setPhotos(new HashSet<>());
            String[] photosStr = gson.fromJson(object.get("photos"), String[].class);
            for (String s : photosStr)
                movie.getPhotos().add(new Photo(s, movie.getId()));
            String[] tags = object.get("cat").getAsString().split(",");
            if (movie.getTags() == null)
                movie.setTags(new HashSet<>());
            for (String s : tags)
                movie.getTags().add(new Tag(s, movie.getId()));
            return movie;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Movie> JsonToMovieList(String jsonString, int page) {
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        List<Movie> list = new ArrayList<>();
        JsonArray movieListJson;
//        if (page <= 1)
//            movieListJson = jsonObject.get("movieList").getAsJsonArray();
//        else
            movieListJson = jsonObject.get("movies").getAsJsonArray();
        for (int i = 0; i < movieListJson.size(); i++) {
            try {
                int id = movieListJson.get(i).getAsJsonObject().get("id").getAsInt();
                String name = movieListJson.get(i).getAsJsonObject().get("nm").getAsString();
                String img = movieListJson.get(i).getAsJsonObject().get("img").getAsString();
                float score = movieListJson.get(i).getAsJsonObject().get("sc").getAsFloat();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(movieListJson.get(i).getAsJsonObject().get("rt").getAsString());
                list.add(new Movie(id, name, img, score, date));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Movie> JsonToReleaseMovieList(String jsonString) {
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class).get("showData").getAsJsonObject();
        List<Movie> list = new ArrayList<>();
        JsonArray movieListJson = jsonObject.get("movies").getAsJsonArray();
        for (int i = 0; i < movieListJson.size(); i++) {
            int id = movieListJson.get(i).getAsJsonObject().get("id").getAsInt();
            String name = movieListJson.get(i).getAsJsonObject().get("nm").getAsString();
            String img = movieListJson.get(i).getAsJsonObject().get("img").getAsString();
            float score = movieListJson.get(i).getAsJsonObject().get("sc").getAsFloat();
            list.add(new Movie(id, name, img, score));
        }
        return list;
    }

    public List<ReleaseInfo> JsonToReleaseInfoList(String jsonString) {
        JsonObject showData = gson.fromJson(jsonString, JsonObject.class).get("showData").getAsJsonObject();
        List<ReleaseInfo> list = new ArrayList<>();
        JsonArray movies = showData.get("movies").getAsJsonArray();
        for (int i = 0; i < movies.size(); i++) {
            JsonArray shows = movies.get(i).getAsJsonObject().get("shows").getAsJsonArray();
            for (int j = 0; j < shows.size(); j++) {
                JsonArray plist = shows.get(j).getAsJsonObject().get("plist").getAsJsonArray();
                if (plist.size() != 0)
                    for (int k = 0; k < plist.size(); k++) {
                        try {
                            int movieId = movies.get(i).getAsJsonObject().get("id").getAsInt();
                            String nm = movies.get(i).getAsJsonObject().get("nm").getAsString();
                            String dt = plist.get(k).getAsJsonObject().get("dt").getAsString();
                            String tm = plist.get(k).getAsJsonObject().get("tm").getAsString();
                            String th = plist.get(k).getAsJsonObject().get("th").getAsString();
                            String lang = plist.get(k).getAsJsonObject().get("lang").getAsString();
                            String tp = plist.get(k).getAsJsonObject().get("tp").getAsString();
                            Double price = null;
                            if (plist.get(k).getAsJsonObject().get("vipPrice") != null)
                                price = plist.get(k).getAsJsonObject().get("vipPrice").getAsDouble();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            Date date = simpleDateFormat.parse(dt + " " + tm);
                            if (price != null)
                                list.add(new ReleaseInfo(new Movie(movieId, nm), date, price, tp, th, lang));
                            else
                                list.add(new ReleaseInfo(new Movie(movieId, nm), date, tp, th, lang));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            }
        }
        return list;
    }

    public Cinema JsonToCinema(String jsonString) {
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        int id = jsonObject.get("cinemaId").getAsInt();
        String area = jsonObject.get("cinemaData").getAsJsonObject().get("addr").getAsString();
        String name = jsonObject.get("cinemaData").getAsJsonObject().get("nm").getAsString();
        return new Cinema(id, name, new Area(area, id));
    }

    public List<Cinema> JsonToCinemaList(String jsonString) {
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        List<Cinema> cinemaList = new ArrayList<>();
        JsonArray jsonArray = jsonObject.get("cinemas").getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            int id = jsonArray.get(i).getAsJsonObject().get("id").getAsInt();
            String name = jsonArray.get(i).getAsJsonObject().get("nm").getAsString();
            String area = jsonArray.get(i).getAsJsonObject().get("addr").getAsString();
            cinemaList.add(new Cinema(id, name, new Area(area, id)));
        }
        return cinemaList;
    }
}
