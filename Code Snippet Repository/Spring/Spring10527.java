	@Test
	public void getForObjectWithCustomUriTemplateHandler() throws Exception {
		DefaultUriBuilderFactory uriTemplateHandler = new DefaultUriBuilderFactory();
		template.setUriTemplateHandler(uriTemplateHandler);

		URI expectedUri = new URI("http://example.com/hotels/1/pic/pics%2Flogo.png/size/150x150");
		given(requestFactory.createRequest(expectedUri, HttpMethod.GET)).willReturn(request);

		given(request.getHeaders()).willReturn(new HttpHeaders());
		given(request.execute()).willReturn(response);
		given(errorHandler.hasError(response)).willReturn(false);

		given(response.getStatusCode()).willReturn(HttpStatus.OK);
		given(response.getHeaders()).willReturn(new HttpHeaders());
		given(response.getBody()).willReturn(StreamUtils.emptyInput());

		Map<String, String> uriVariables = new HashMap<>(2);
		uriVariables.put("hotel", "1");
		uriVariables.put("publicpath", "pics/logo.png");
		uriVariables.put("scale", "150x150");

		String url = "http://example.com/hotels/{hotel}/pic/{publicpath}/size/{scale}";
		template.getForObject(url, String.class, uriVariables);

		verify(response).close();
	}
