package pers.yang.questionnaire.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import pers.yang.questionnaire.entity.Questionnaire;
import pers.yang.questionnaire.entity.Response;
import pers.yang.questionnaire.utils.ResponseUtil;
import pers.yang.questionnaire.utils.UserUtil;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yang Zhenman
 * @since 2021-04-20
 */
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {
	/**
	 * 创建问卷
	 * @param questionnaire {"userId","name","remark","type","startTime","expireTime"}, 用户编号、问卷名称、问卷类型、开始时间、过期时间、和问卷状态(0:未发布,1:已发布),默认0
	 * @return 成功或失败信息
	 */
	@PostMapping
	@RequiresPermissions("questionnaire:addOne")
	public Response addOne(@RequestBody Questionnaire questionnaire) {
		questionnaire.setUserId(UserUtil.getUserId());
		questionnaire.insert();
		return ResponseUtil.ok(questionnaire.selectById());
	}

	/**
	 * 根据问卷编号获取问卷信息
	 * @param id 问卷编号
	 * @return 问卷信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("questionnaire:getOne")
	public Response getOne(@PathVariable("id") Integer id) {
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setId(id);
		return ResponseUtil.ok(questionnaire.selectById());
	}

	/**
	 * 查看问卷列表
	 * @return 所有问卷
	 */
	@GetMapping("/")
	@RequiresPermissions("questionnaire:getAll")
	public Response getAll() {
		Questionnaire questionnaire = new Questionnaire();
		return ResponseUtil.ok(questionnaire.selectAll());
	}

	/**
	 * 修改一条问卷信息
	 * @param questionnaire  {"userId","name","remark","type","startTime","expireTime"}, 用户编号、问卷名称、问卷类型、开始时间、过期时间、和问卷状态(0:未发布,1:已发布,默认0)
	 * @return 更新后的问卷信息
	 */
	@PutMapping("/")
	@RequiresPermissions("questionnaire:editOne")
	public Response editOne(@RequestBody Questionnaire questionnaire) {
		questionnaire.updateById();
		return ResponseUtil.ok(questionnaire);
	}

	/**
	 * 根据问卷编号删除问卷信息
	 * @param id 问卷编号
	 * @return 成功或失败信息
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("questionnaire:delOne")
	public Response delOne(@PathVariable("id") Integer id) {
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setId(id);
		return ResponseUtil.ok(questionnaire.deleteById());
	}
}

