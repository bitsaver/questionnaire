package pers.yang.questionnaire.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yang Zhenman
 * @since 2021-04-20
 */
@TableName("t_user_choice")
public class UserChoice extends Model<UserChoice> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userQuesitonnarieId;

    private Integer questionId;

    private Integer optionId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserQuesitonnarieId() {
        return userQuesitonnarieId;
    }

    public void setUserQuesitonnarieId(Integer userQuesitonnarieId) {
        this.userQuesitonnarieId = userQuesitonnarieId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserChoice{" +
        "id=" + id +
        ", userQuesitonnarieId=" + userQuesitonnarieId +
        ", questionId=" + questionId +
        ", optionId=" + optionId +
        "}";
    }
}
