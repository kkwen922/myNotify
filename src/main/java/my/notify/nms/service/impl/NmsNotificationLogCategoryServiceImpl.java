package my.notify.nms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import my.notify.nms.dto.NmsNotificationCategoryParam;
import my.notify.nms.mapper.NmsNotificationCategoryMapper;
import my.notify.nms.model.NmsNotificationCategory;
import my.notify.nms.service.NmsNotificationLogCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @author : kevin Chang
 * @since : 2022/3/2
 */

@Service
@Slf4j
public class NmsNotificationLogCategoryServiceImpl extends ServiceImpl<NmsNotificationCategoryMapper, NmsNotificationCategory> implements NmsNotificationLogCategoryService {

    /**
     * listAll
     * @return
     */
    @Override
    public List<NmsNotificationCategory> listAll() {
        QueryWrapper<NmsNotificationCategory> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByDesc(NmsNotificationCategory::getSort);
        return list(wrapper);
    }

    /**
     * getCateList
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public Page<NmsNotificationCategory> getCateList(String keyword, Integer pageSize, Integer pageNum) {
        Page<NmsNotificationCategory> page = new Page<>(pageNum, pageSize);
        QueryWrapper<NmsNotificationCategory> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("status");
        LambdaQueryWrapper<NmsNotificationCategory> lambda = wrapper.lambda();

        log.info("search keyword: {}", keyword);

        if (StrUtil.isNotEmpty(keyword)) {
            lambda.like(NmsNotificationCategory::getName, keyword);
            lambda.or().like(NmsNotificationCategory::getNote, keyword);
        }

        return page(page, wrapper);
    }

    /**
     * create
     * @param nmsNotificationCategoryParam
     * @return
     */
    @Override
    public NmsNotificationCategory create(NmsNotificationCategoryParam nmsNotificationCategoryParam) {
        NmsNotificationCategory nmsNotificationCategory = new NmsNotificationCategory();
        BeanUtils.copyProperties(nmsNotificationCategoryParam, nmsNotificationCategory);
        nmsNotificationCategory.setCreateTime(new Date());

        //查詢是否有相同發送方式的紀錄
        QueryWrapper<NmsNotificationCategory> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(NmsNotificationCategory::getName, nmsNotificationCategory.getName());
        List<NmsNotificationCategory> categoryList = list(wrapper);
        if (categoryList.size() > 0) {
            return null;
        }
        baseMapper.insert(nmsNotificationCategory);
        return nmsNotificationCategory;
    }

    /**
     * updateStatus
     * @param id
     * @param status
     * @return
     */
    @Override
    public boolean updateStatus(Long id, Integer status) {
        NmsNotificationCategory nmsNotificationCategory = new NmsNotificationCategory();
        nmsNotificationCategory.setId(id);
        nmsNotificationCategory.setStatus(status);
        return updateById(nmsNotificationCategory);
    }

    /**
     * update
     * @param id
     * @param nmsNotificationCategory
     * @return
     */
    @Override
    public boolean update(Long id, NmsNotificationCategory nmsNotificationCategory) {
        nmsNotificationCategory.setId(id);
        boolean success = updateById(nmsNotificationCategory);
        return success;
    }

    /**
     * create
     * @param snsCate
     * @return
     */
    @Override
    public boolean create(NmsNotificationCategory snsCate) {
        snsCate.setCreateTime(new Date());
        snsCate.setSort(0);
        return save(snsCate);
    }
}
