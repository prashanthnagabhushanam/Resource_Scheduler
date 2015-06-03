package com.jpmc.cca.util;

import java.util.Random;

import com.jpmc.cca.bean.Group;
import com.jpmc.cca.bean.Message;

public class MessageGenerator {

	private static int nextValue = 0;
	private static Random random = new Random(5);
	private static Group group1 = new Group("Group1", 1);
	private static Group group2 = new Group("Group2", 2);
	private static Group group3 = new Group("Group3", 3);
	private static Group group4 = new Group("Group4", 4);
	private static Group group5 = new Group("Group5", 5);
	private static Group group6 = new Group("Group6", 6);
	private static Group group7 = new Group("Group7", 7);
	private static Group group8 = new Group("Group8", 8);
	private static Group group9 = new Group("Group9", 9);
	private static Group group10 = new Group("Group10", 10);
	private static Group[] groups = { group1, group2, group3, group4 ,group5, group6, group7, group8,group10, group9};

	public static Message getMessage() {
		Message message = new Message(nextValue++,
				groups[random.nextInt(10)]);
		return message;
	}
}
