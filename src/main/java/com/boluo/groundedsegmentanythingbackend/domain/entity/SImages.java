package com.boluo.groundedsegmentanythingbackend.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import lombok.Data;

/**
 * 用于存放风格迁移后的图片
 * @TableName s_images
 */
@TableName(value ="s_images")
@Data
public class SImages implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 生成的url
     */
    @TableField(value = "url")
    private String url;

    /**
     * 生成的时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 父亲的id
     */
    @TableField(value = "pid")
    private Long pid;

    /**
     * 描述语
     */
    @TableField(value = "text_prompt")
    private String textPrompt;

    /**
     * 盒子阈值
     */
    @TableField(value = "box_treshold")
    private String boxTreshold;

    /**
     * 文本的阈值
     */
    @TableField(value = "text_treshold")
    private String textTreshold;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "SImages{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                ", pid=" + pid +
                ", textPrompt='" + textPrompt + '\'' +
                ", boxTreshold='" + boxTreshold + '\'' +
                ", textTreshold='" + textTreshold + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SImages)) return false;
        SImages sImages = (SImages) o;
        return Objects.equals(id, sImages.id) && Objects.equals(url, sImages.url) && Objects.equals(createTime, sImages.createTime) && Objects.equals(pid, sImages.pid) && Objects.equals(textPrompt, sImages.textPrompt) && Objects.equals(boxTreshold, sImages.boxTreshold) && Objects.equals(textTreshold, sImages.textTreshold);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, createTime, pid, textPrompt, boxTreshold, textTreshold);
    }
}