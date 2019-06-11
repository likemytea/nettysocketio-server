package com.chenxing.managesystem.designPattern.abstractFactory;

import com.chenxing.managesystem.designPattern.ordinaryFactory.MyInterface;

public class DesignTest {
	public static void main(String[] args) {
		Provider p = new MyFactoryTwo();
		MyInterface myi = p.produce();
		myi.print();
	}
}
