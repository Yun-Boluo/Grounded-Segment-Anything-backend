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
 * 用于存放图片路径等
 * @TableName p_images
 */
@TableName(value ="p_images")
@Data
public class PImages implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;



    /**
     * 图片名称,后端随机生成
     */
    @TableField(value = "image_name")
    private String imageName;

    /**
     * 记录上传时间
     */
    @TableField(value = "upload_time")
    private Date uploadTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @Override
    public String toString() {
        return "PImages{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", imageName='" + imageName + '\'' +
                ", uploadTime=" + uploadTime +
                '}';
    }

    /**
     * 存放路径
     */
    @TableField(value = "url")
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PImages pImages = (PImages) o;
        return Objects.equals(id, pImages.id) && Objects.equals(url, pImages.url) && Objects.equals(imageName, pImages.imageName) && Objects.equals(uploadTime, pImages.uploadTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, imageName, uploadTime);
    }

    public PImages(Long id, String imageName, Date uploadTime, String url) {
        this.id = id;
        this.imageName = imageName;
        this.uploadTime = uploadTime;
        this.url = url;
    }

    public PImages() {
    }
}