package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.api.vo.UserFavoritesListVO;
import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.NewBeeMallException;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.dao.NewBeeMallGoodsMapper;
import ltd.newbee.mall.dao.UserFavoritesMapper;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.UserFavorites;
import ltd.newbee.mall.service.UserFavoritesService;
import ltd.newbee.mall.util.BeanUtil;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserFavoritesServiceImpl implements UserFavoritesService {

    @Autowired
    private UserFavoritesMapper userFavoritesMapper;

    @Autowired
    private NewBeeMallGoodsMapper goodsMapper;

    @Override
    public PageResult queryUserFavorites(PageQueryUtil pageUtil, String lang) {
        int pageNum = pageUtil.getPage();
        Long userId = (Long) pageUtil.get("userId");
        List<UserFavorites> userFavorites = userFavoritesMapper.selectByUserId(userId, pageNum);
        int total = userFavoritesMapper.selectTotalUserFavorites(pageUtil);
        return new PageResult(mapUserFavoritesToUserFavoritesVO(userFavorites, lang), total, pageUtil.getLimit(), pageNum);
    }

    private List<UserFavoritesListVO> mapUserFavoritesToUserFavoritesVO(List<UserFavorites> userFavorites, String lang) {
        if (CollectionUtils.isEmpty(userFavorites)) {
            return Collections.emptyList();
        }

        /**
         * 根据关注列表查询商品信息
         */
        List<Long> newBeeMallGoodsIds = userFavorites.stream().map(UserFavorites::getGoodsId).collect(Collectors.toList());
        List<NewBeeMallGoods> newBeeMallGoods = goodsMapper.selectByPrimaryKeys(newBeeMallGoodsIds);
        Map<Long, NewBeeMallGoods> newBeeMallGoodsMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(newBeeMallGoods)) {
            newBeeMallGoodsMap = newBeeMallGoods.stream().collect(Collectors.toMap(NewBeeMallGoods::getGoodsId, Function.identity(), (entity1, entity2) -> entity1));
        }

        /**
         * 映射为vo
         */
        List<UserFavoritesListVO> voList = new ArrayList<>();
        for (UserFavorites userFavorite: userFavorites) {
            UserFavoritesListVO vo = new UserFavoritesListVO();
            NewBeeMallGoods favoriteGoods = newBeeMallGoodsMap
                    .get(userFavorite.getGoodsId());
            vo.setFavoritesId(userFavorite.getFavoritesId());
            vo.setGoodsCoverImg(favoriteGoods.getGoodsCoverImg());
            vo.setGoodsId(favoriteGoods.getGoodsId());
            vo.setSellingPrice(favoriteGoods.getSellingPrice());

            switch (lang) {
                case Constants.I18N_LANG_EN:
                    vo.setGoodsName(favoriteGoods.getGoodsEnName());break;
                case Constants.I18N_LANG_CA:
                    vo.setGoodsName(favoriteGoods.getGoodsCaName());break;
                default:
                    vo.setGoodsName(favoriteGoods.getGoodsZhName());break;
            }
            voList.add(vo);
        }
        return voList;
    }


    @Override
    public String saveUserFavorites(Long userId, Long goodsId) {
        UserFavorites userFavorites = userFavoritesMapper.selectByUserIdAndGoodsId(userId, goodsId);
        if (userFavorites != null) {
            //已存在则修改该记录
            NewBeeMallException.fail(ServiceResultEnum.SHOPPING_CART_ITEM_EXIST_ERROR.getResult());
        }

        UserFavorites favorites = new UserFavorites();
        favorites.setCreateTime(new Date());
        favorites.setUpdateTime(new Date());
        favorites.setGoodsId(goodsId);
        favorites.setUserId(userId);
        //保存记录
        if (userFavoritesMapper.insertSelective(favorites) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }
}
