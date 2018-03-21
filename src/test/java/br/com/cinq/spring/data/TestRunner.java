package br.com.cinq.spring.data;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import br.com.cinq.spring.data.sample.application.Application;

public class TestRunner {
	public static void main(String[] args) {
		Application.main(new String[0]);
		boolean fail=false;
		Result result = JUnitCore.runClasses(AllTests.class);
		
		for (Failure failure : result.getFailures()) {
			System.out.println("The test "+failure.getTestHeader()+" has faield");
			fail=true;
		}
		if(!fail) {
			System.out.println("THE SYSTEM HAS PASSED THROUGH ALL THE TESTS");
			System.exit(1);
		}else	
			System.out.println("THE SYSTEM HAS FAILS");
		System.exit(0);
	}
}
