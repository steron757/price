package com;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.ProductDao;
import com.model.Product;
import com.task.GetDataTask;

@SuppressWarnings("unused")
public class Test {
	public static void main(String[] args) {
		new GetDataTask().run();
	}
}
