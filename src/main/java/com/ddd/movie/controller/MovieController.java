package com.ddd.movie.controller;

import com.ddd.movie.pojo.Movie;
import com.ddd.movie.service.MovieService;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

@Controller
@RequestMapping("/manage/movie")
public class MovieController {
    @Resource(name = "movieService")
    MovieService movieService;
    Gson gson = new Gson();
    private Logger logger = Logger.getLogger("HomeController");

    @RequestMapping({"", "/"})
    public String index(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, Model model) {
        PageInfo pageinfo = movieService.findPageByMybatis(page, 20);
        model.addAttribute("movies", pageinfo.getList());
        model.addAttribute("pages", pageinfo);
        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("isLogin", subject.isAuthenticated());
//        String t = "{\"albumImg\": \"http://p1.meituan.net/moviemachine/0f7311517ee008b2ac56e2d152a60a0659152.jpg\", \"availableEpisodes\": 0, \"awardUrl\": \"\", \"backgroundColor\": \"#401A1A\", \"bingeWatch\": 48343, \"bingeWatchst\": 0, \"cat\": \"\\u7231\\u60c5,\\u5947\\u5e7b,\\u5192\\u9669\", \"comScorePersona\": true, \"commented\": false, \"dir\": \"\\u76d6\\u00b7\\u91cc\\u5947\", \"distributions\": [{\"movieScoreLevel\": \"9-10\\u5206\", \"proportion\": \"76.13\"}, {\"movieScoreLevel\": \"5-8\\u5206\", \"proportion\": \"20.74\"}, {\"movieScoreLevel\": \"1-4\\u5206\", \"proportion\": \"3.13\"}], \"dra\": \"\\u5728\\u5145\\u6ee1\\u5f02\\u57df\\u98ce\\u60c5\\u7684\\u53e4\\u4ee3\\u963f\\u62c9\\u4f2f\\u738b\\u56fd\\uff0c\\u5584\\u826f\\u7684\\u7a77\\u5c0f\\u5b50\\u963f\\u62c9\\u4e01\\uff08\\u83ab\\u7eb3\\u00b7\\u9a6c\\u82cf\\u5fb7 \\u9970\\uff09\\u548c\\u52c7\\u6562\\u7684\\u8309\\u8389\\u516c\\u4e3b\\uff08\\u5a1c\\u5965\\u7c73\\u00b7\\u65af\\u79d1\\u7279 \\u9970\\uff09\\u6d6a\\u6f2b\\u9082\\u9005\\uff0c\\u5728\\u53ef\\u4ee5\\u6ee1\\u8db3\\u4e3b\\u4eba\\u4e09\\u4e2a\\u613f\\u671b\\u7684\\u795e\\u706f\\u7cbe\\u7075\\u5e2e\\u52a9\\u4e0b\\uff0c\\u4e24\\u4eba\\u8e0f\\u4e0a\\u4e86\\u4e00\\u6b21\\u5bfb\\u627e\\u771f\\u7231\\u548c\\u81ea\\u6211\\u7684\\u9b54\\u5e7b\\u5192\\u9669\\u3002\", \"dur\": 128, \"egg\": false, \"enm\": \"Aladdin\", \"episodeDur\": 128, \"episodes\": 0, \"fra\": \"\\u7f8e\\u56fd\", \"frt\": \"2019-05-24\", \"globalReleased\": true, \"id\": 1207959, \"img\": \"http://p1.meituan.net/w.h/moviemachine/42ef79dd1d894b67dd57de383d753c03556009.jpg\", \"latestEpisode\": 0, \"modcsSt\": false, \"movieType\": 0, \"multiPub\": true, \"musicName\": \"A Whole New World(End Title)\", \"musicNum\": 1, \"musicStar\": \"ZAYN;Zhavia Ward\", \"nm\": \"\\u963f\\u62c9\\u4e01\", \"onSale\": true, \"onlinePlay\": false, \"orderSt\": 0, \"oriLang\": \"\\u82f1\\u8bed\", \"photos\": [\"http://p0.meituan.net/w.h/movie/405a620b8be8e3b05d08fcde86be781b6998847.jpg\", \"http://p0.meituan.net/w.h/movie/5fefaddb6f48d4abc231ca31e69ae3653283770.jpg\", \"http://p1.meituan.net/w.h/movie/29e5f546810bebc25277814b5c23b3657405349.jpg\", \"http://p0.meituan.net/w.h/movie/f771f437748e96ff822bf8fefb9635938558047.jpg\", \"http://p0.meituan.net/w.h/movie/ea6267eb1fabc38931109c0f3f53c9ba5077408.jpg\", \"http://p1.meituan.net/w.h/movie/28323187cd3d6f918264dad9b14f97976543825.jpg\", \"http://p0.meituan.net/w.h/movie/4abe613310352326dee24dde942afbe36333299.jpg\", \"http://p1.meituan.net/w.h/movie/b5f2723d063b6147320abef9f91211915053453.jpg\", \"http://p0.meituan.net/w.h/movie/a4a8c753b30d5411f0634e98fbfd7e3a5774977.jpg\", \"http://p0.meituan.net/w.h/movie/b1fdaf0492ebe324a1bae220345462fb5274666.jpg\", \"http://p0.meituan.net/w.h/movie/4601ad60da4319916e0499380eafc4d56616900.jpg\", \"http://p0.meituan.net/w.h/movie/bf286811c7d7b5874e6bfeda7fca4b5b4742857.jpg\", \"http://p0.meituan.net/w.h/movie/73ec645929a453a91eaee3dd8f34b6315643521.jpg\", \"http://p0.meituan.net/w.h/movie/1e113040fcddc02baec34fcad2fa7a094255694.jpg\", \"http://p0.meituan.net/w.h/movie/9f383422c753acd4df388ac2d41a15a06477261.jpg\", \"http://p0.meituan.net/w.h/movie/aa5e867abae8558b44992ec762bf36515414820.jpg\", \"http://p0.meituan.net/w.h/movie/65b0b6568b5a347470c80b6bfc77eb004849718.jpg\", \"http://p0.meituan.net/w.h/movie/b8ff6a13f9d1002d595cc3642b2e416b4970861.jpg\", \"http://p1.meituan.net/w.h/movie/da9661623695a5bde30905af6335aca715924615.jpg\", \"http://p0.meituan.net/w.h/movie/e5701f382f0eb57d8c212b78440c6abe6237583.jpg\"], \"pn\": 124, \"preScorePersona\": false, \"proScore\": 0, \"proScoreNum\": 1, \"pubDesc\": \"2019-05-24\\u5927\\u9646\\u4e0a\\u6620\", \"rt\": \"2019-05-24\", \"sc\": 9, \"scm\": \"\", \"scoreLabel\": \"\\u732b\\u773c\\u8d2d\\u7968\\u8bc4\\u5206\", \"showst\": 3, \"snum\": 84486, \"src\": \"\\u7f8e\\u56fd\", \"star\": \"\\u6885\\u7eb3\\u00b7\\u739b\\u7d22\\u5fb7,\\u5a1c\\u5965\\u7c73\\u00b7\\u65af\\u79d1\\u7279,\\u9a6c\\u5c14\\u4e07\\u00b7\\u80af\\u624e\\u91cc\", \"type\": 0, \"vd\": \"http://maoyan.meituan.net/movie/videos/854x48095c33864348242c198b684b673a62723.mp4\", \"ver\": \"2D/3D/IMAX 3D/\\u4e2d\\u56fd\\u5de8\\u5e55/\\u5168\\u666f\\u58f0\", \"videoImg\": \"http://p0.meituan.net/movie/4d1e718c52d5567432b22fce1e1c72ad265892.jpg@640w_360h_1e_1c\", \"videoName\": \"\\u201c\\u5e74\\u5ea6\\u6700\\u5f3a\\u9526\\u9ca4\\u201d\\u5e26\\u4f60\\u8f7b\\u677e\\u8fc7\\u5468\\u672b\", \"videourl\": \"http://maoyan.meituan.net/movie/videos/854x48095c33864348242c198b684b673a62723.mp4\", \"viewedSt\": 0, \"vnum\": 55, \"vodFreeSt\": 0, \"vodPlay\": false, \"vodSt\": 0, \"watched\": 83952, \"wish\": 48343, \"wishst\": 0, \"version\": \"v3d imax\"}";
//        Movie movie = new JsonToObjectUtils().JsonToMovie(t);
//        System.out.println(movie.getId() + movie.getName());
//        for (Photo p : movie.getPhotos())
//            System.out.println(p.getUrl());
//        movieService.add(movie);
//        Movie movie = movieService.findByName("阿拉丁");
//        System.out.println(movie.getId());
        model.addAttribute("page", page);
        return "home";
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public String browseMovie(@PathVariable("id") int id, Model model) {
//        Movie movie = movieService.findById(id);
//        Movie movie = movieService.findByIdJson(id);
//        model.addAttribute("movie", movie);
        return "redirect:/" + id;
    }

    @RequestMapping(value = "/edit/{id}")
//    @ResponseBody
    public String editMovie(@PathVariable("id") int id,
                            @RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "director", required = false) String director,
                            @RequestParam(value = "releasetime", required = false) String releaseTime,
                            @RequestParam(value = "type", required = false) String type,
                            @RequestParam(value = "starring", required = false) String starring,
                            @RequestParam(value = "language", required = false) String language,
                            @RequestParam(value = "length", required = false) String length,
                            @RequestParam(value = "otherName", required = false) String otherName,
                            @RequestParam(value = "introduction", required = false) String introduction,
                            @RequestParam(value = "score", required = false) Double score,
                            @RequestParam(value = "img", required = false) String img,
                            Model model) {
        Movie movie = movieService.findById(id);
        if (name == null && director == null && releaseTime == null && type == null && starring == null && score == null && language == null && length == null && otherName == null && introduction == null && img == null) {
            model.addAttribute("movie", movie);
            return "editMovie";
        } else {
            if (!name.equals(""))
                movie.setName(name);
            if (!director.equals(""))
                movie.setDirector(director);
            if (!releaseTime.equals("")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    movie.setReleaseDate(dateFormat.parse(releaseTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (!type.equals(""))
                movie.setType(type);
            if (!starring.equals(""))
                movie.setStarring(starring);
            if (!language.equals(""))
                movie.setLanguage(language);
            if (!length.equals(""))
                movie.setLength(length);
            if (!otherName.equals(""))
                movie.setOtherName(otherName);
            if (!introduction.equals(""))
                movie.setIntroduction(introduction);
            if (!img.equals(""))
                movie.setAlbumImg(img);
            if (score != null) {
                double s = score;
                movie.setScore((float) s);
            }
            movieService.update(movie);
            return "redirect:/manage/movie";
        }
    }

    @RequestMapping(value = "/del/{id}")
    public String delMovie(@PathVariable("id") int id) {
        Movie movie = movieService.findById(id);
        if (movie != null)
            movieService.delete(movie);
        return "redirect:/manage/movie";
    }

    @RequestMapping(value = "/add")
    public String addMoviePage(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "director", required = false) String director,
                               @RequestParam(value = "releasetime", required = false) String releaseTime,
                               @RequestParam(value = "type", required = false) String type,
                               @RequestParam(value = "starring", required = false) String starring,
                               @RequestParam(value = "language", required = false) String language,
                               @RequestParam(value = "length", required = false) String length,
                               @RequestParam(value = "otherName", required = false) String otherName,
                               @RequestParam(value = "introduction", required = false) String introduction,
                               @RequestParam(value = "score", required = false) Double score,
                               @RequestParam(value = "img", required = false) String img) {
        if (name == null && director == null && releaseTime == null && type == null && starring == null && score == null && language == null && length == null && otherName == null && introduction == null && img == null) {
            return "addMovie";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Movie movie = new Movie(name, director, dateFormat.parse(releaseTime));
                movieService.add(movie);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return "redirect:/manage/movie";
        }
    }
}