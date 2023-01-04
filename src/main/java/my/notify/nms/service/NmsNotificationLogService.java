package my.notify.nms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import my.notify.nms.model.NmsNotificationLog;

import java.util.List;

/**
 * @author : kevin Chang
 * @since : 2022/2/24
 * <p>
 * 發送紀錄管理Service
 */
public interface NmsNotificationLogService extends IService<NmsNotificationLog> {

    /**
     * 獲得所有發送紀錄
     * @return
     */
    List<NmsNotificationLog> listAll();

    /**
     * 創建發送紀錄
     * @param nmsNotificationLog
     * @return
     */
    boolean create(NmsNotificationLog nmsNotificationLog);

    /**
     * 分頁發送紀錄
     * @param status
     * @param keyword
     * @param name
     * @param beginTime
     * @param endTime
     * @param pageSize
     * @param pageNum
     * @return
     */
    Page<NmsNotificationLog> getLogList(Integer status, String keyword, String name, String beginTime, String endTime, Integer pageSize, Integer pageNum);

    /**
     * 更新發送狀態，以及API回覆結果時間
     * @param id
     * @param status
     * @return
     */
    boolean updateStatus(Long id, Integer status);

    //Page<NmsNotificationLog> getNotificationLogAndCate(Integer status,String keyword, String name,String beginTime,String endTime, Integer pageSize, Integer pageNum);

}
