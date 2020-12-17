package ltd.newbee.mall.api.mapper;

import ltd.newbee.mall.api.vo.NewBeeMallGoodsDetailVO;
import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MallGoodsDetailVoMapper  implements I18nLangMapper<NewBeeMallGoodsDetailVO, NewBeeMallGoods> {


    @Override
    public NewBeeMallGoodsDetailVO mapI18nLangFromEntity(NewBeeMallGoodsDetailVO target,
                                                         NewBeeMallGoods source, String lang) {
        if (target == null || source == null || lang == null) {
            return null;
        }
        switch (lang){
            case Constants.I18N_LANG_EN:
                if (StringUtils.isEmpty(source.getGoodsEnName()) || StringUtils.isEmpty(source.getGoodsEnIntro())
                        || StringUtils.isEmpty(source.getGoodsDetailEnContent())) {
                    setZhFields(target, source);
                } else {
                    target.setGoodsName(source.getGoodsEnName());
                    target.setGoodsIntro(source.getGoodsEnIntro());
                    target.setGoodsDetailContent(source.getGoodsDetailEnContent());
                }
                break;
            case Constants.I18N_LANG_CA:
                if (StringUtils.isEmpty(source.getGoodsCaName()) || StringUtils.isEmpty(source.getGoodsCaIntro())
                        || StringUtils.isEmpty(source.getGoodsDetailCaContent())) {
                    setZhFields(target, source);
                } else {
                    target.setGoodsName(source.getGoodsCaName());
                    target.setGoodsIntro(source.getGoodsCaIntro());
                    target.setGoodsDetailContent(source.getGoodsDetailCaContent());
                }
                break;
            default:
                setZhFields(target, source);
                break;
        }
        return null;
    }

    private void setZhFields(NewBeeMallGoodsDetailVO target, NewBeeMallGoods source) {
        target.setGoodsName(source.getGoodsZhName());
        target.setGoodsIntro(source.getGoodsZhIntro());
        target.setGoodsDetailContent(source.getGoodsDetailZhContent());
    }
}
