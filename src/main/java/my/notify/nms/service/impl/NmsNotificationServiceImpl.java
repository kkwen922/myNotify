package my.notify.nms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import my.notify.nms.mapper.NmsNotificationLogMapper;
import my.notify.nms.model.NmsNotificationLog;
import my.notify.nms.service.NmsNotificationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author : kevin Chang
 * @since : 2022/2/24
 *
 * 發送紀錄管理Service實現類
 *
 */
@Service
@Slf4j
public class NmsNotificationServiceImpl extends ServiceImpl<NmsNotificationLogMapper, NmsNotificationLog> implements NmsNotificationLogService {

    @Autowired
    private NmsNotificationLogMapper notificationLogMapper;

    /**
     * listAll
     * @return
     */
    @Override
    public List<NmsNotificationLog> listAll() {
        QueryWrapper<NmsNotificationLog> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByDesc(NmsNotificationLog::getId);
        return list(wrapper);
    }

    /**
     * create
     * @param nmsNotificationLog
     * @return
     */
    @Override
    public boolean create(NmsNotificationLog nmsNotificationLog) {
        nmsNotificationLog.setCreateTime(new Date());
        return save(nmsNotificationLog);
    }

    /**
     * getLogList
     * @param status
     * @param keyword
     * @param name
     * @param beginTime
     * @param endTime
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public Page<NmsNotificationLog> getLogList(Integer status,String keyword, String name,String beginTime,String endTime, Integer pageSize, Integer pageNum) {

        log.info("Notification getLogList:{},{},{}",status,keyword,name);

        Page<NmsNotificationLog> page = new Page<>(pageNum, pageSize);
        QueryWrapper<NmsNotificationLog> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        LambdaQueryWrapper<NmsNotificationLog> lambda = wrapper.lambda();

        log.info("status: {}",status);
        log.info("keyword: {}",keyword);
        log.info("snsType name: {}",name);
        log.info("createTime  between : {} and {} ",beginTime,endTime);

        if(status !=null){
            lambda.eq(NmsNotificationLog::getStatus,status);
        }

        if (StrUtil.isNotEmpty(name)) {
            lambda.eq(NmsNotificationLog::getName, name);
        }

        if (StrUtil.isNotEmpty(keyword)) {
            lambda.like(NmsNotificationLog::getSendTo, keyword);
            lambda.or().like(NmsNotificationLog::getContext, keyword);
        }

        if (StrUtil.isNotEmpty(beginTime)) {
            lambda.gt(NmsNotificationLog::getCreateTime, beginTime);
        }

        if (StrUtil.isNotEmpty(endTime)) {
            lambda.lt(NmsNotificationLog::getCreateTime, endTime);
        }

        return page(page, wrapper);
    }

    /**
     * updateStatus
     * @param id
     * @param status
     * @return
     */
    @Override
    public boolean updateStatus(Long id, Integer status) {
        NmsNotificationLog nmsNotificationLog = new NmsNotificationLog();
        nmsNotificationLog.setId(id);
        nmsNotificationLog.setStatus(status);
        nmsNotificationLog.setResponseTime(new Date());
        return updateById(nmsNotificationLog);
    }


}
