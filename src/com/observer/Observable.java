package com.observer;

import java.util.List;

/**
 * Observer 
 * 
 * @author Gang.Chen
 *
 */
public interface Observable {

	public void notifyObserver(List<Object> productList);
}
