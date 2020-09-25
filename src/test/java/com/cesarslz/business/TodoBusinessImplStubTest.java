package com.cesarslz.business;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.cesarslz.data.api.TodoService;
import com.cesarslz.data.api.TodoServicesStub;

public class TodoBusinessImplStubTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAStub() {
		TodoService todoServiceStub = new TodoServicesStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
		
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		
		assertEquals(2, filteredTodos.size());
	}

}
