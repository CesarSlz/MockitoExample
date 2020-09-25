package com.cesarslz.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import com.cesarslz.data.api.TodoService;

//@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplInjectMockTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	TodoService todoServiceMock;
	
	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Lear to dance");
		
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		
		assertEquals(2, filteredTodos.size());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_withEmptyList() {
		
		List<String> todos = Arrays.asList();
		
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		
		assertEquals(0, filteredTodos.size());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDD() {
		
		// Given - setup
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Lear to dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		// When - actual mehtod call(SUT - Logic Business)
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		
		// Then - assert
		assertThat(filteredTodos.size(), is(2));
	}
	
	@Test
	public void testDeleteTodosRelatedToSpring_usingBDD() {
		
		// Given - setup
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		// When - actual mehtod call(SUT - Logic Business)
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		// Then - assert
		verify(todoServiceMock).deleteTodo("Learn to dance"); // Verifica que un metodo es llamado
		then(todoServiceMock).should().deleteTodo("Learn to dance");
		
		verify(todoServiceMock, times(1)).deleteTodo("Learn to dance"); // Verifica cuantas veces un metodo es llamado
		
		verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC"); // Verificar que un metodo np es llamado
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
	}
	
	@Test
	public void testDeleteTodosRelatedToSpring_usingBDD_argumentCapture() {
		
		// Given - setup
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		// When - actual mehtod call(SUT - Logic Business)
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		// Then - assert
		then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
		
		assertThat(stringArgumentCaptor.getValue(), is("Learn to dance"));
	}
	
	@Test
	public void testDeleteTodosRelatedToSpring_usingBDD_argumentCaptureMultiplesTimes() {
		
		// Given - setup
		List<String> todos = Arrays.asList("Learn to Rock and Roll", "Learn Spring", "Learn to dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		// When - actual mehtod call(SUT - Logic Business)
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		// Then - assert
		then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
		
		assertThat(stringArgumentCaptor.getAllValues().size(), is(2));
	}

}

