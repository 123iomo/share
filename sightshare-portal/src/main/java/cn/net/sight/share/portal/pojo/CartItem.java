package cn.net.sight.share.portal.pojo;

/**
 * 
 * <p>
 * Title: CartItem
 * </p>
 * <p>
 * Description: 商品条目在购物车中显示POJO
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年2月6日下午1:24:08
 * @version 1.0
 */
public class CartItem {

	private Long id;
	private String title;
	private Integer number;
	private Long price;
	private String image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
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

}
