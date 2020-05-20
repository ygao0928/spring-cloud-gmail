package ltd.ygao.gmail.member.controller;

import java.util.Arrays;
import java.util.Map;


import ltd.ygao.gmail.member.feign.CouponFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ltd.ygao.gmail.member.entity.UmsMemberEntity;
import ltd.ygao.gmail.member.service.UmsMemberService;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.common.utils.R;


/**
 * 会员
 *
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-08 11:29:31
 */
@RestController
@RequestMapping("member/umsmember")
public class UmsMemberController {
    @Autowired
    private UmsMemberService umsMemberService;

    @Autowired
    private CouponFeignService couponFeignService;
@RequestMapping("/coupons")
    public R test(){
        UmsMemberEntity umsMemberEntity = new UmsMemberEntity();
        umsMemberEntity.setNickname("高勇");
        R coupons = couponFeignService.memberCoupons();
        return R.ok().put("member",umsMemberEntity).put("coupons",coupons.get("coupons"));
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
// @RequiresPermissions("member:umsmember:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = umsMemberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//@RequiresPermissions("member:umsmember:info")
    public R info(@PathVariable("id") Long id) {
        UmsMemberEntity umsMember = umsMemberService.getById(id);

        return R.ok().put("umsMember", umsMember);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//  @RequiresPermissions("member:umsmember:save")
    public R save(@RequestBody UmsMemberEntity umsMember) {
        umsMemberService.save(umsMember);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//  @RequiresPermissions("member:umsmember:update")
    public R update(@RequestBody UmsMemberEntity umsMember) {
        umsMemberService.updateById(umsMember);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//   @RequiresPermissions("member:umsmember:delete")
    public R delete(@RequestBody Long[] ids) {
        umsMemberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
