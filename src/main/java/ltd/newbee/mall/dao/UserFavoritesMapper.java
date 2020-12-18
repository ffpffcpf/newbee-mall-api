package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.UserFavorites;
import ltd.newbee.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFavoritesMapper {

    List<UserFavorites> selectByUserId(@Param("userId") Long userId, @Param("pageNum") int pageNum);
    int selectTotalUserFavorites(PageQueryUtil pageQueryUtil);
}
