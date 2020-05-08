package ltd.ygao.gmail.coupon.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ltd.ygao.gmail.coupon.entity.SmsSeckillSkuRelationEntity;
import ltd.ygao.gmail.coupon.service.SmsSeckillSkuRelationService;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.common.utils.R;



/**
 * 秒杀活动商品关联
 *
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-08 11:21:20
 */
@RestController
@RequestMapping("coupon/smsseckillskurelation")
public class SmsSeckillSkuRelationController {
    @Autowired
    private SmsSeckillSkuRelationService smsSeckillSkuRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
// @RequiresPermissions("coupon:smsseckillskurelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = smsSeckillSkuRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//@RequiresPermissions("coupon:smsseckillskurelation:info")
    public R info(@PathVariable("id") Long id){
		SmsSeckillSkuRelationEntity smsSeckillSkuRelation = smsSeckillSkuRelationService.getById(id);

        return R.ok().put("smsSeckillSkuRelation", smsSeckillSkuRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//  @RequiresPermissions("coupon:smsseckillskurelation:save")
    public R save(@RequestBody SmsSeckillSkuRelationEntity smsSeckillSkuRelation){
		smsSeckillSkuRelationService.save(smsSeckillSkuRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//  @RequiresPermissions("coupon:smsseckillskurelation:update")
    public R update(@RequestBody SmsSeckillSkuRelationEntity smsSeckillSkuRelation){
		smsSeckillSkuRelationService.updateById(smsSeckillSkuRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//   @RequiresPermissions("coupon:smsseckillskurelation:delete")
    public R delete(@RequestBody Long[] ids){
		smsSeckillSkuRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
