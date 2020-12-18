package ltd.newbee.mall.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserFavorites {
    private Long favoritesId;

    private Long userId;

    private Long goodsId;

    private Byte isDeleted;

    private Date createTime;

    private Date updateTime;
}
