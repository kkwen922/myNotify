package my.notify.nms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import my.notify.nms.dto.NmsNotificationCategoryParam;
import my.notify.nms.model.NmsNotificationCategory;

import java.util.List;

/**
 * @author : kevin Chang
 * @since : 2022/3/2
 *
 * 發送類型Service
 */
public interface NmsNotificationLogCategoryService extends IService<NmsNotificationCategory> {

    /**
     * 獲得所有發送分類
     * @return
     */
    List<NmsNotificationCategory> listAll();


    /**
     * 分頁發送分類
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    Page<NmsNotificationCategory> getCateList(String keyword, Integer pageSize, Integer pageNum);


    /**
     * 創建發送分類資訊
     * @param nmsNotificationCategoryParam
     * @return
     */
    NmsNotificationCategory create(NmsNotificationCategoryParam nmsNotificationCategoryParam);

    /**
     * 修改分類啟用狀態
     * @param id
     * @param status
     * @return
     */
    boolean updateStatus(Long id, Integer status);

    /**
     * 修改指定發送分類訊息
     * @param id
     * @param nmsNotificationCategory
     * @return
     */
    boolean update(Long id, NmsNotificationCategory nmsNotificationCategory);

    /**
     * 創建發送分類訊息
     * @param nmsNotificationCategory
     * @return
     */
    boolean create(NmsNotificationCategory nmsNotificationCategory);
}
