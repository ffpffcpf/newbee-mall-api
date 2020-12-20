package ltd.newbee.mall.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserFavoritesListVO {

    @ApiModelProperty("收藏项id")
    private Long favoritesId;

    @ApiModelProperty("商品id")
    private Long goodsId;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("商品图片")
    private String goodsCoverImg;

    @ApiModelProperty("商品价格")
    private Integer sellingPrice;
}
