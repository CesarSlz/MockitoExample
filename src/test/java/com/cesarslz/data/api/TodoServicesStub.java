package com.cesarslz.data.api;

import java.util.Arrays;
import java.util.List;

public class TodoServicesStub implements TodoService{

	public List<String> retrieveTodos(String user) {
		
		return Arrays.asList("Learn Spring MVC", "Learn Spring", "Lear to dance");
	}

	public void deleteTodo(String todo) {
		
	}

}
