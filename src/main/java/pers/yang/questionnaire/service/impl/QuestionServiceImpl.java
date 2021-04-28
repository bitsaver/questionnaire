package pers.yang.questionnaire.service.impl;

import pers.yang.questionnaire.entity.Question;
import pers.yang.questionnaire.mapper.QuestionMapper;
import pers.yang.questionnaire.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2021-04-20
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

}
