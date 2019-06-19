package com.springboot.bootlearn.bootDao;

import com.springboot.bootlearn.bootMoudle.MybatisMoudle;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
@Mapper
public interface MybatisDao {

    @Select("SELECT\n" +
            "\tID,\n" +
            "\tQUEUE_NAME,\n" +
            "\t(\n" +
            "\tCASE\n" +
            "\t\t\t\n" +
            "\t\t\tWHEN QUEUE_STATUS = '0' THEN\n" +
            "\t\t\t'等待同步' \n" +
            "\t\t\tWHEN QUEUE_STATUS = '1' THEN\n" +
            "\t\t\t'同步中' \n" +
            "\t\t\tWHEN QUEUE_STATUS = '2' THEN\n" +
            "\t\t\t'同步成功' \n" +
            "\t\t\tWHEN QUEUE_STATUS = '3' THEN\n" +
            "\t\t\t'同步失败' \n" +
            "\t\t\tWHEN QUEUE_STATUS = '4' THEN\n" +
            "\t\t\t'无需同步' ELSE '并无此状态' \n" +
            "\t\tEND \n" +
            "\t\t) QUEUE_STATUS,\n" +
            "\t\tREDIS_NAME,\n" +
            "\t\t(\n" +
            "\t\tCASE\n" +
            "\t\t\t\t\n" +
            "\t\t\t\tWHEN REDIS_STATUS = '0' THEN\n" +
            "\t\t\t\t'等待放入缓存' \n" +
            "\t\t\t\tWHEN REDIS_STATUS = '1' THEN\n" +
            "\t\t\t\t'正在放入缓存' \n" +
            "\t\t\t\tWHEN REDIS_STATUS = '2' THEN\n" +
            "\t\t\t\t'放入缓存成功' \n" +
            "\t\t\t\tWHEN REDIS_STATUS = '3' THEN\n" +
            "\t\t\t\t'放入缓存失败' \n" +
            "\t\t\t\tWHEN REDIS_STATUS = '4' THEN\n" +
            "\t\t\t\t'无需放入缓存' ELSE '并无此状态' \n" +
            "\t\t\tEND \n" +
            "\t\t\t) REDIS_STATUS \n" +
            "\t\tFROM\n" +
            "\t\t\tSPBOOT_MYB;")
    public List<MybatisMoudle> select();

    @Select("SELECT\n" +
            "\tID,\n" +
            "\tQUEUE_NAME,\n" +
            "\t(\n" +
            "\tCASE\n" +
            "\t\t\t\n" +
            "\t\t\tWHEN QUEUE_STATUS = '0' THEN\n" +
            "\t\t\t'等待同步' \n" +
            "\t\t\tWHEN QUEUE_STATUS = '1' THEN\n" +
            "\t\t\t'同步中' \n" +
            "\t\t\tWHEN QUEUE_STATUS = '2' THEN\n" +
            "\t\t\t'同步成功' \n" +
            "\t\t\tWHEN QUEUE_STATUS = '3' THEN\n" +
            "\t\t\t'同步失败' \n" +
            "\t\t\tWHEN QUEUE_STATUS = '4' THEN\n" +
            "\t\t\t'无需同步' ELSE '并无此状态' \n" +
            "\t\tEND \n" +
            "\t\t) QUEUE_STATUS,\n" +
            "\t\tREDIS_NAME,\n" +
            "\t\t(\n" +
            "\t\tCASE\n" +
            "\t\t\t\t\n" +
            "\t\t\t\tWHEN REDIS_STATUS = '0' THEN\n" +
            "\t\t\t\t'等待放入缓存' \n" +
            "\t\t\t\tWHEN REDIS_STATUS = '1' THEN\n" +
            "\t\t\t\t'正在放入缓存' \n" +
            "\t\t\t\tWHEN REDIS_STATUS = '2' THEN\n" +
            "\t\t\t\t'放入缓存成功' \n" +
            "\t\t\t\tWHEN REDIS_STATUS = '3' THEN\n" +
            "\t\t\t\t'放入缓存失败' \n" +
            "\t\t\t\tWHEN REDIS_STATUS = '4' THEN\n" +
            "\t\t\t\t'无需放入缓存' ELSE '并无此状态' \n" +
            "\t\t\tEND \n" +
            "\t\t\t) REDIS_STATUS \n" +
            "\t\tFROM\n" +
            "\t\t\tSPBOOT_MYB \n" +
            "\t\tWHERE\n" +
            "\t\t\tID = #{perKeId};")
    MybatisMoudle selectByPerKeId(String perKeId);

    int insertMybatis(MybatisMoudle mybatisMoudle);

    @Delete("DELETE FROM SPBOOT_MYB WHERE SPBOOT_MYB.ID=#{perKeId};")
    int deleteById(String perKeId);

    int updateMybatis(MybatisMoudle mybatisMoudle);
}
