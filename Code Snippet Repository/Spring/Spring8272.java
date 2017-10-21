	private ClientHttpResponse getClientHttpResponse(
			HttpMethod httpMethod, URI uri, HttpHeaders requestHeaders, byte[] requestBody) {

		try {
			MockHttpServletResponse servletResponse = mockMvc
					.perform(request(httpMethod, uri).content(requestBody).headers(requestHeaders))
					.andReturn()
					.getResponse();

			HttpStatus status = HttpStatus.valueOf(servletResponse.getStatus());
			byte[] body = servletResponse.getContentAsByteArray();
			MockClientHttpResponse clientResponse = new MockClientHttpResponse(body, status);
			clientResponse.getHeaders().putAll(getResponseHeaders(servletResponse));
			return clientResponse;
		}
		catch (Exception ex) {
			byte[] body = ex.toString().getBytes(StandardCharsets.UTF_8);
			return new MockClientHttpResponse(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
