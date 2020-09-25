package com.cesarslz.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void letsMockListSizeMethod() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		assertEquals(2, listMock.size());
	}
	
	@Test
	public void letsMockListSizeMethod_ReturnMultipleValues() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2).thenReturn(3);
		assertEquals(2, listMock.size());
		assertEquals(3, listMock.size());
	}
	
	@Test
	public void letsMockListGet() {
		List listMock = mock(List.class);
		when(listMock.get(0)).thenReturn("Cesar Salazar");
		assertEquals("Cesar Salazar", listMock.get(0));
		assertEquals(null, listMock.get(1));
	}

	// Argument matchers
	@Test
	public void letsMockListGet_WithArgumentMatcher() {
		List listMock = mock(List.class);
		when(listMock.get(anyInt())).thenReturn("Cesar Salazar");
		assertEquals("Cesar Salazar", listMock.get(0));
		assertEquals("Cesar Salazar", listMock.get(1));
	}
	
	@Test(expected = RuntimeException.class)
	public void letsMockList_ThrowAnException() {
		List listMock = mock(List.class);
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));

		listMock.get(0);
	}
	
	@Test(expected = RuntimeException.class)
	public void letsMockList_ThrowAnException2() {
		List listMock = mock(List.class);
		when(listMock.subList(anyInt(), 5)).thenThrow(new RuntimeException("Something"));

		listMock.subList(0, 5);
	}
	
	@Test
	public void letsMockListGet_usingBDD() {
		// Given - setup
		List<String> listMock = mock(List.class);
		given(listMock.get(anyInt())).willReturn("Cesar Salazar");
		
		// When - listMock.get(0)
		String firstElement = listMock.get(0);
		
		// Then - assert
		assertThat(firstElement, is("Cesar Salazar"));
	}
}
