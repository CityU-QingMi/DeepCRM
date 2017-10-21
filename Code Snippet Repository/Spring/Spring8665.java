	@Test
	public void getResponseAllMatches() throws Exception {
		when(matcher1.matches(request)).thenReturn(true);
		when(connection1.getResponse(request)).thenReturn(expectedResponse);
		WebResponse response = webConnection.getResponse(request);

		assertThat(response, sameInstance(expectedResponse));
		verify(matcher1).matches(request);
		verifyNoMoreInteractions(matcher2, connection2, defaultConnection);
		verify(connection1).getResponse(request);
	}
