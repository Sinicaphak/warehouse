package com.p1.dao;

import com.p1.pojo.Song;
import com.p1.pojo.SongExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SongMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table song
     *
     * @mbg.generated Tue Feb 28 17:37:46 GMT+08:00 2023
     */
    long countByExample(SongExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table song
     *
     * @mbg.generated Tue Feb 28 17:37:46 GMT+08:00 2023
     */
    int deleteByExample(SongExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table song
     *
     * @mbg.generated Tue Feb 28 17:37:46 GMT+08:00 2023
     */
    int deleteByPrimaryKey(Integer rid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table song
     *
     * @mbg.generated Tue Feb 28 17:37:46 GMT+08:00 2023
     */
    int insert(Song row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table song
     *
     * @mbg.generated Tue Feb 28 17:37:46 GMT+08:00 2023
     */
    int insertSelective(Song row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table song
     *
     * @mbg.generated Tue Feb 28 17:37:46 GMT+08:00 2023
     */
    List<Song> selectByExample(SongExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table song
     *
     * @mbg.generated Tue Feb 28 17:37:46 GMT+08:00 2023
     */
    Song selectByPrimaryKey(Integer rid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table song
     *
     * @mbg.generated Tue Feb 28 17:37:46 GMT+08:00 2023
     */
    int updateByExampleSelective(@Param("row") Song row, @Param("example") SongExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table song
     *
     * @mbg.generated Tue Feb 28 17:37:46 GMT+08:00 2023
     */
    int updateByExample(@Param("row") Song row, @Param("example") SongExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table song
     *
     * @mbg.generated Tue Feb 28 17:37:46 GMT+08:00 2023
     */
    int updateByPrimaryKeySelective(Song row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table song
     *
     * @mbg.generated Tue Feb 28 17:37:46 GMT+08:00 2023
     */
    int updateByPrimaryKey(Song row);
}