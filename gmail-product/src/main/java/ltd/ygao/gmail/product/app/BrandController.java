package ltd.ygao.gmail.product.app;

import java.util.Arrays;
import java.util.Map;

import ltd.ygao.gmail.common.valid.AddGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ltd.ygao.gmail.product.entity.BrandEntity;
import ltd.ygao.gmail.product.service.BrandService;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.common.utils.R;


/**
 * 品牌
 *
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-07 16:54:29
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId) {
        BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody BrandEntity brand/*, BindingResult result*/) {
//        if (result.hasErrors()){
//            Map<String,String> map=new HashMap<>();
//           result.getFieldErrors().forEach((item)->{
//               //获取错误属性的名字
//               String field = item.getField();
//               //获取错误消息
//               String message = item.getDefaultMessage();
//               map.put(field,message);
//           });
//           return R.error(400,"提交的数据不合法").put("data",map);
//        }
        brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @Transactional
    @RequestMapping("/update")
    public R update(@RequestBody BrandEntity brand) {
        brandService.updateDetail(brand);
        //TODO 更新其他关联
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds) {
        brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
