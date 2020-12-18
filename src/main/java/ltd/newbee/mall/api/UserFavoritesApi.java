package ltd.newbee.mall.api;

import io.swagger.annotations.ApiOperation;
import ltd.newbee.mall.api.vo.UserFavoritesListVO;
import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.config.annotation.TokenToMallUser;
import ltd.newbee.mall.entity.MallUser;
import ltd.newbee.mall.service.UserFavoritesService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserFavoritesApi {

    @Autowired
    private UserFavoritesService userFavoritesService;

    @GetMapping("favorites")
    @ApiOperation("查询用户收藏夹")
    public Result<PageResult<List<UserFavoritesListVO>>> getUserFavorites(
            @RequestHeader(value = "lang", defaultValue = "") String lang,
            @TokenToMallUser MallUser user,
            @RequestParam(value = "pageNum") Integer pageNum
    ) {
        Map<String, Object> pageQueryParams = new HashMap<>(4);
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        pageQueryParams.put("userId", user.getUserId());
        pageQueryParams.put("page", pageNum);
        pageQueryParams.put("limit", Constants.FAVORITES_PAGE_NUMBER);

        PageQueryUtil pageUtil = new PageQueryUtil(pageQueryParams);
        return ResultGenerator.genSuccessResult(userFavoritesService.queryUserFavorites(pageUtil, lang));
    }

}
