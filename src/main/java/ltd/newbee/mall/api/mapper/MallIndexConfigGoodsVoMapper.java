package ltd.newbee.mall.api.mapper;

import ltd.newbee.mall.api.vo.NewBeeMallIndexConfigGoodsVO;
import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MallIndexConfigGoodsVoMapper implements I8nLangMapper<NewBeeMallIndexConfigGoodsVO, NewBeeMallGoods> {

    @Override
    public NewBeeMallIndexConfigGoodsVO mapI18nLangFromEntity(NewBeeMallIndexConfigGoodsVO target, NewBeeMallGoods source, String lang) {
        if (target == null || source == null || lang == null) {
            return null;
        }
        switch (lang) {
            case Constants.I18N_LANG_EN:
                if (StringUtils.isEmpty(source.getGoodsEnName()) || StringUtils.isEmpty(source.getGoodsEnIntro())) {
                    setZhFields(target, source);
                } else {
                    target.setGoodsName(source.getGoodsEnName());
                    target.setGoodsIntro(source.getGoodsEnIntro());
                }
                break;
            case Constants.I18N_LANG_CA:
                if (StringUtils.isEmpty(source.getGoodsCaName()) || StringUtils.isEmpty(source.getGoodsCaIntro())) {
                    setZhFields(target, source);
                } else {
                    target.setGoodsName(source.getGoodsCaName());
                    target.setGoodsIntro(source.getGoodsCaIntro());
                }
                break;
            default:
                setZhFields(target, source);
                break;
        }
        return target;
    }

    private void setZhFields(NewBeeMallIndexConfigGoodsVO target, NewBeeMallGoods source) {
        target.setGoodsName(source.getGoodsZhName());
        target.setGoodsIntro(source.getGoodsZhIntro());
    }
}
