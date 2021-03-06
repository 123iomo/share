package cn.net.sight.share.pojo;

public class TbItemLink {
    private Long itemId;

    private String itemTitle;

    private String itemLink;

    private String linkPassword;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle == null ? null : itemTitle.trim();
    }

    public String getItemLink() {
        return itemLink;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink == null ? null : itemLink.trim();
    }

    public String getLinkPassword() {
        return linkPassword;
    }

    public void setLinkPassword(String linkPassword) {
        this.linkPassword = linkPassword == null ? null : linkPassword.trim();
    }
}