package ltd.newbee.mall.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryInfoVo implements Serializable {

    @ApiModelProperty("当前分类id")
    protected Long categoryId;

    @ApiModelProperty("当前分类级别")
    protected Byte categoryLevel;

    @ApiModelProperty("当前一级分类名称")
    protected String categoryName;
}
