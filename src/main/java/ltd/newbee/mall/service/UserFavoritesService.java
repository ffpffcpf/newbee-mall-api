package ltd.newbee.mall.service;

import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

public interface UserFavoritesService {
    PageResult queryUserFavorites(PageQueryUtil pageUtil, String lang);
}
