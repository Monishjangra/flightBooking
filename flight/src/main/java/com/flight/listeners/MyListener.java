package com.flight.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("test start: " +result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("test success: " +result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("on failure: " +result.getTestName());
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("on start: " +context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("on finish: " +context.getName());
	}
	

}
