	@Test
	public void mapNullTemplateVariable() throws Exception {
		given(requestFactory.createRequest(new URI("http://example.com/-foo"), HttpMethod.GET))
				.willReturn(request);
		given(request.execute()).willReturn(response);
		given(errorHandler.hasError(response)).willReturn(false);
		HttpStatus status = HttpStatus.OK;
		given(response.getStatusCode()).willReturn(status);
		given(response.getStatusText()).willReturn(status.getReasonPhrase());

		Map<String, String> vars = new HashMap<>(2);
		vars.put("first", null);
		vars.put("last", "foo");
		template.execute("http://example.com/{first}-{last}", HttpMethod.GET, null, null, vars);

		verify(response).close();
	}
