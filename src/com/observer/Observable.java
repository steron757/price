package com.observer;

import java.util.List;

public interface Observable {

	public void notifyObserver(List<Object> productList);
}
