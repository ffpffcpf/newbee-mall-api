package ltd.newbee.mall.api.mapper;

import ltd.newbee.mall.api.vo.CategoryInfoVo;
import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.entity.GoodsCategory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MallIndexCategoryVOMapper implements I18nLangMapper<CategoryInfoVo, GoodsCategory> {

    /**
     * 根据lang决定vo的CategoryName展示的语言
     *
     * @param vo
     * @param category
     * @param lang     default:中文，en-US:英文，ca-ca:柬文
     * @return
     */
    public CategoryInfoVo mapI18nLangFromEntity(CategoryInfoVo vo, GoodsCategory category, String lang) {
        if (vo == null || category == null || lang == null) {
            return null;
        }
        switch (lang) {
            case Constants.I18N_LANG_EN:
                if (StringUtils.isEmpty(category.getCategoryEnName())) {
                    vo.setCategoryName(category.getCategoryZhName());
                } else {
                    vo.setCategoryName(category.getCategoryEnName());
                }
                break;
            case Constants.I18N_LANG_CA:
                if (StringUtils.isEmpty(category.getCategoryCaName())) {
                    vo.setCategoryName(category.getCategoryZhName());
                } else {
                    vo.setCategoryName(category.getCategoryCaName());
                }
                break;
            default:
                vo.setCategoryName(category.getCategoryZhName());
        }
        return vo;
    }
}
