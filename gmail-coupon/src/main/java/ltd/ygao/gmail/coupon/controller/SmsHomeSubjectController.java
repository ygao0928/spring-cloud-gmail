package ltd.ygao.gmail.coupon.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ltd.ygao.gmail.coupon.entity.SmsHomeSubjectEntity;
import ltd.ygao.gmail.coupon.service.SmsHomeSubjectService;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.common.utils.R;



/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-08 11:21:20
 */
@RestController
@RequestMapping("coupon/smshomesubject")
public class SmsHomeSubjectController {
    @Autowired
    private SmsHomeSubjectService smsHomeSubjectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
// @RequiresPermissions("coupon:smshomesubject:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = smsHomeSubjectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//@RequiresPermissions("coupon:smshomesubject:info")
    public R info(@PathVariable("id") Long id){
		SmsHomeSubjectEntity smsHomeSubject = smsHomeSubjectService.getById(id);

        return R.ok().put("smsHomeSubject", smsHomeSubject);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//  @RequiresPermissions("coupon:smshomesubject:save")
    public R save(@RequestBody SmsHomeSubjectEntity smsHomeSubject){
		smsHomeSubjectService.save(smsHomeSubject);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//  @RequiresPermissions("coupon:smshomesubject:update")
    public R update(@RequestBody SmsHomeSubjectEntity smsHomeSubject){
		smsHomeSubjectService.updateById(smsHomeSubject);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//   @RequiresPermissions("coupon:smshomesubject:delete")
    public R delete(@RequestBody Long[] ids){
		smsHomeSubjectService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
