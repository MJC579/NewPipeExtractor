package org.schabi.newpipe.extractor;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class MetaInfo implements Serializable {

    private String title = "";
    private String text = "";
    private List<URL> urls = new ArrayList<>();
    private List<String> urlTexts = new ArrayList<>();

    public MetaInfo(@Nonnull final String title, @Nonnull final String text,
                    @Nonnull final List<URL> urls, @Nonnull final List<String> urlTexts) {
        this.title = title;
        this.text = text;
        this.urls = urls;
        this.urlTexts = urlTexts;
    }

    public MetaInfo() {
    }

    /**
     * @return Title of the info. Can be empty.
     */
    @Nonnull
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nonnull final String title) {
        this.title = title;
    }

    @Nonnull
    public String getText() {
        return text;
    }

    public void setText(@Nonnull final String text) {
        this.text = text;
    }

    @Nonnull
    public List<URL> getUrls() {
        return urls;
    }

    public void setUrls(@Nonnull final List<URL> urls) {
        this.urls = urls;
    }

    public void addUrl(@Nonnull final URL url) {
        urls.add(url);
    }

    @Nonnull
    public List<String> getUrlTexts() {
        return urlTexts;
    }

    public void setUrlTexts(@Nonnull final List<String> urlTexts) {
        this.urlTexts = urlTexts;
    }

    public void addUrlText(@Nonnull final String urlText) {
        urlTexts.add(urlText);
    }
}
