package com.geekutil.modules.sys.task.schedule;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @author Asens
 */
@Component("testTask")
@Log4j2
public class TestTask {

	public void test(){
		log.info("test方法，正在被执行");
	}
}
