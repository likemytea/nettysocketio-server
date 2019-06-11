package com.chenxing.managesystem.designPattern.abstractFactory;

import com.chenxing.managesystem.designPattern.ordinaryFactory.MyClassOne;
import com.chenxing.managesystem.designPattern.ordinaryFactory.MyInterface;

public class MyFactoryOne implements Provider {

	@Override
	public MyInterface produce() {
		// TODO Auto-generated method stub
		return new MyClassOne();
	}

}
