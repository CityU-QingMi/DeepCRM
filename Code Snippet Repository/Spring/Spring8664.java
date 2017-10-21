	@Test
	public void getResponseDefault() throws Exception {
		when(defaultConnection.getResponse(request)).thenReturn(expectedResponse);
		WebResponse response = webConnection.getResponse(request);

		assertThat(response, sameInstance(expectedResponse));
		verify(matcher1).matches(request);
		verify(matcher2).matches(request);
		verifyNoMoreInteractions(connection1, connection2);
		verify(defaultConnection).getResponse(request);
	}
