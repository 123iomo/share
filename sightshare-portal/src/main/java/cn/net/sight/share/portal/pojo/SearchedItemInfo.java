package cn.net.sight.share.portal.pojo;

import cn.net.sight.share.pojo.TbItem;

public class SearchedItemInfo extends TbItem {

	public String[] getImages() {
		String image = getImage();
		if (image != null) {
			String[] images = image.split(",");
			return images;
		}
		return null;
	}
}
