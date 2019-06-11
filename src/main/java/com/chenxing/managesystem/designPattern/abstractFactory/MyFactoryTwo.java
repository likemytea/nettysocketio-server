package com.chenxing.managesystem.designPattern.abstractFactory;

import com.chenxing.managesystem.designPattern.ordinaryFactory.MyClassTwo;
import com.chenxing.managesystem.designPattern.ordinaryFactory.MyInterface;

public class MyFactoryTwo implements Provider {

	@Override
	public MyInterface produce() {
		return new MyClassTwo();
	}

}
