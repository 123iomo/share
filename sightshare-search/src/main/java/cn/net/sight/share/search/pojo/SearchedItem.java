package cn.net.sight.share.search.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * <p>Title: SearchedItem</p>
 * <p>Description: SOLR查询引擎结果POJO</p>
 * <p>Company: www.sight.net.cn</p> 
 * @author: aaron9727
 * @date 2017年1月29日下午12:26:06
 * @version 1.0
 */
public class SearchedItem {

	private String id;
	private String title;
	private String sell_point;
	private Long price;
	private String image;
	private String category_name;
	private String item_desc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSell_point() {
		return sell_point;
	}

	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getItem_desc() {
		return item_desc;
	}

	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
	
	@JsonIgnore
	public String[] getImages() {
		if (image != null) {
			String[] images = image.split(",");
			return images;
		}
		return null;
	}

}
