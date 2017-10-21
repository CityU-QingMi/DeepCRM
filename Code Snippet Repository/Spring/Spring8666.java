	@Test
	public void getResponseSecondMatches() throws Exception {
		when(matcher2.matches(request)).thenReturn(true);
		when(connection2.getResponse(request)).thenReturn(expectedResponse);
		WebResponse response = webConnection.getResponse(request);

		assertThat(response, sameInstance(expectedResponse));
		verify(matcher1).matches(request);
		verify(matcher2).matches(request);
		verifyNoMoreInteractions(connection1, defaultConnection);
		verify(connection2).getResponse(request);
	}
