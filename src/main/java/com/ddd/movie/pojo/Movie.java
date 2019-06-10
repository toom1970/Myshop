package com.ddd.movie.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "movie")
public class Movie implements Serializable {
    private int id;
    private String name; //nm
    private String director; //dir 导演
    private Date releaseDate; //frt
    private String writer;
    private String starring; //star
    private String type; //cat
    private String area; //fra
    private String language; //oriLang
    private String length; //dur
    private String otherName; //enm
    private String introduction; //dra
    private Set<Tag> tags;
    private Set<Image> images;
    private Set<Photo> photos;
    private String albumImg;//封面
    private float score;//评分


    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "mid")
    @Transient
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "mid")
    @Transient
    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "mid")
    @Transient
    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    @Column(name = "albumimg")
    public String getAlbumImg() {
        return albumImg;
    }

    public void setAlbumImg(String albumImg) {
        this.albumImg = albumImg;
    }

    @Column(name = "score")
    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Basic
    @Column(name = "releasedate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Transient
    @JsonIgnore
    public String getFormatDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(releaseDate);
    }

    @Column(name = "writer")
    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Transient
    @JsonIgnore
    public List<String> getWriterList() {
        return Arrays.asList(writer.split(","));
    }

    public void setWriterList(List<String> writerList) {
        StringBuilder stringBuilder = new StringBuilder();
        String sp = "";
        for (String s : writerList) {
            stringBuilder.append(sp).append(s);
            sp = ",";
        }
        this.writer = stringBuilder.toString();
    }

    @Column(name = "starring")
    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    @Transient
    @JsonIgnore
    public List<String> getStarringList() {
        return Arrays.asList(starring.split(","));
    }

    public void setStarringList(List<String> starringList) {
        StringBuilder stringBuilder = new StringBuilder();
        String sp = "";
        for (String s : starringList) {
            stringBuilder.append(sp).append(s);
            sp = ",";
        }
        this.starring = stringBuilder.toString();
    }

    @Column(name = "area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(name = "length")
    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Column(name = "othername")
    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    @Transient
    @JsonIgnore
    public List<String> getOtherNameList() {
        return Arrays.asList(otherName.split(","));
    }

    public void setOtherNameList(List<String> otherNameList) {
        StringBuilder stringBuilder = new StringBuilder();
        String sp = "";
        for (String s : otherNameList) {
            stringBuilder.append(sp).append(s);
            sp = ",";
        }
        this.otherName = stringBuilder.toString();
    }

    public Movie(int id, String name, String director, Date releaseDate, String formatDate) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.releaseDate = releaseDate;
    }

    public Movie() {
    }

    public Movie(int id, String name, String albumImg, float score, Date date) {
        this.id = id;
        this.name = name;
        this.albumImg = albumImg;
        this.score = score;
        this.releaseDate = date;
    }

    public Movie(int id, String name, String albumImg, float score) {
        this.id = id;
        this.name = name;
        this.albumImg = albumImg;
        this.score = score;
    }

    public Movie(String name, String director, Date releaseDate) {
        this.name = name;
        this.director = director;
        this.releaseDate = releaseDate;
    }

    public Movie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (name != null ? !name.equals(movie.name) : movie.name != null) return false;
        if (director != null ? !director.equals(movie.director) : movie.director != null) return false;
        if (releaseDate != null ? !releaseDate.equals(movie.releaseDate) : movie.releaseDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        return result;
    }
}
