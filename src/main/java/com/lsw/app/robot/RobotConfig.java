package com.lsw.app.robot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//xml 대신에 java를 이용해서 객체 생성

@Configuration //xml 역할을 하는 annotation
public class RobotConfig {
	
	// 기존 <bean class = ""> 이 역할을 메서드가 대신 함 
	@Bean("ra")
	RobotArm makeArm() {
		return new RobotArm();
	}
	
	@Bean
	Robot makeRobot() {
		Robot robot = new Robot();
		robot.setRobotArm(makeArm());
		
		return robot;
		
	}
	
	
	
	
}
