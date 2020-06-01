package ltd.ygao.gmail.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ltd.ygao.gmail.product.entity.AttrEntity;
import ltd.ygao.gmail.product.service.AttrAttrgroupRelationService;
import ltd.ygao.gmail.product.service.AttrService;
import ltd.ygao.gmail.product.service.CategoryService;
import ltd.ygao.gmail.product.vo.AttrGroupRelationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ltd.ygao.gmail.product.entity.AttrGroupEntity;
import ltd.ygao.gmail.product.service.AttrGroupService;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.common.utils.R;


/**
 * 属性分组
 *
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-07 16:54:29
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    AttrService attrService;
    @Autowired
    AttrAttrgroupRelationService relationService;

    //
    @PostMapping("/attr/relation")
    public R addRelation(@RequestBody List<AttrGroupRelationVo> vos) {
        relationService.saveBatch(vos);
        return R.ok();
    }

    ///product/attrgroup/attr/relation/delete
    @PostMapping("/relation/delete")
    public R deleteRelation(@RequestBody AttrGroupRelationVo[] vos) {
        attrService.deleteRelation(vos);
        return R.ok();
    }

    ///product/attrgroup/{attrgroupId}/noattr/relation
    @GetMapping("/{attrgroupId}/noattr/relation")
    public R attrNoRelation(@PathVariable("attrgroupId") Long attrgroupId,
                            @RequestParam Map<String, Object> params) {
        PageUtils page = attrService.getNoRelationAttr(params, attrgroupId);
        return R.ok().put("data", page);
    }

    @RequestMapping(value = "/{attrgroupId}/attr/relation", method = RequestMethod.GET)
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId) {
        List<AttrEntity> attrEntities = attrService.getRelationAttr(attrgroupId);
        System.out.println(attrEntities);
        return R.ok().put("data", attrEntities);
    }

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")

    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable("catelogId") Long catelogId) {
        //  PageUtils page = attrGroupService.queryPage(params);
        PageUtils page = attrGroupService.queryPage(params, catelogId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getAttrGroupId();
        Long[] path = categoryService.findCatelogPath(catelogId);
        System.out.println(Arrays.asList(path));
        attrGroup.setCatelogPath(path);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
