package pers.yang.questionnaire.AutoGenerator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 * 代码生成器演示
 * </p>
 */
public class MpGenerator {

	final static String dirPath = "D://";

	/**
	 * <p>
	 * MySQL 生成演示
	 * </p>
	 */
	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();

//        String dirPath = System.getProperty("user.dir");
		// 全局配置
		GlobalConfig globalConfig = new GlobalConfig()
				.setOutputDir(MpGenerator.dirPath)
//                .setOutputDir(dirPath)
				.setAuthor("Yang Zhenman")
				.setActiveRecord(true)// 不需要ActiveRecord特性的请改为false
				.setFileOverride(true) //是否覆盖
				.setBaseResultMap(true)// XML ResultMap
				.setEnableCache(false)// XML 二级缓存
				.setBaseColumnList(true)// XML columList
				.setIdType(IdType.AUTO)
				.setServiceName("%sService")
				.setBaseResultMap(true)
				.setBaseColumnList(true);

		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		// gc.setMapperName("%sDao");
		// gc.setXmlName("%sMapper");
		// gc.setServiceName("MP%sService");
		// gc.setServiceImplName("%sServiceDiy");
		// gc.setControllerName("%sAction");
		mpg.setGlobalConfig(globalConfig);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig()
				.setDbType(DbType.MYSQL)
				.setDriverName("com.mysql.cj.jdbc.Driver")
				.setUsername("root")
				.setPassword("root")
				.setUrl("jdbc:mysql://localhost:3306/questionnaire?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");

		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig()
				.setCapitalMode(true)// 全局大写命名 ORACLE 注意
				.setTablePrefix("t_")// 此处可以修改为您的表前缀
				.setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
				.setInclude("t_user", "t_user_role", "t_role", "t_role_permission", "t_permission",
						"t_questionnaire", "t_question", "t_option",
						"t_user_questionnaire", "t_user_choice", "t_user_answer"); // 需要生成的表

		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("pers.yang")
				.setModuleName("questionnaire")
				.setController("controller")
				.setEntity("entity")
				.setMapper("mapper")
				.setService("service")
				.setXml("mapper");

		mpg.setPackageInfo(pc);

		// 执行生成
		mpg.execute();

	}

}