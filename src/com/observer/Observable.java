package com.observer;

import java.util.List;

/**
 * Observer </br>
 * 16/11/2013
 * 
 * @author Gang.Chen
 *
 */
public interface Observable {

	public void notifyObserver(List<Object> productList);
}
