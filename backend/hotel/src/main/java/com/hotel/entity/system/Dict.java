package com.hotel.entity.system;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 通用字典
 *
 * @author 小李哞哞
 * @date 2023/5/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@ApiModel("字典实体")
public class Dict {

    @ApiModelProperty("字典id(主键)")
    private Integer id;

    @ApiModelProperty("字典名称")
    private String name;

    @ApiModelProperty("字典值")
    private String value;

    @ApiModelProperty("字典类型")
    private String type;
}
