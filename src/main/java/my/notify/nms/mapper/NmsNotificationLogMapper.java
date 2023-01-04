package my.notify.nms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.notify.nms.model.NmsNotificationLog;


/**
 * @author : kevin Chang
 * @since : 2022/2/24
 *
 * <p>
 * 訊息發送紀錄資訊 Mapper 接口
 * </p>
 *
 */
public interface NmsNotificationLogMapper  extends BaseMapper<NmsNotificationLog> {

//    String querySql =
//            "SELECT c.id,oo.name,c.context,c.status,c.create_time,c.response_time,c.send_to,c.category_id" +
//            "FROM nms_notification_log c left outer join  nms_notification_category oo on c.category_id = oo.id";
//
//    String wrapperSql = "SELECT * from ( " + querySql + " ) AS q ${ew.customSqlSegment}";
//
//    @Select(querySql)
//    List<NmsNotificationLog> getNotificationLogAndCate();
//
//
//    /**
//     * 分頁查詢
//     * @param page
//     * @param queryWrapper
//     * @return
//     */
//    @Select(wrapperSql)
//    Page<NmsNotificationLog> page(Page page, @Param("ew") Wrapper queryWrapper);
}
