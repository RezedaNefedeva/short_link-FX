package org.rezeda.diplomfx.models;

public class Link {
    private String link;

    private String small_link;

    public Link() {
    }

    public Link(String link, String small_link) {
        this.link = link;
        this.small_link = small_link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSmall_link() {
        return small_link;
    }

    public void setSmall_link(String small_link) {
        this.small_link = small_link;
    }
}
