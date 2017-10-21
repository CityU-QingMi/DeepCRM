	@SuppressWarnings("")
	@Test
	public void requestBodyAdvice() throws IOException {

		RequestBodyAdvice requestAdvice = Mockito.mock(RequestBodyAdvice.class);
		ResponseBodyAdvice<String> responseAdvice = Mockito.mock(ResponseBodyAdvice.class);
		List<Object> advice = Arrays.asList(requestAdvice, responseAdvice);
		RequestResponseBodyAdviceChain chain = new RequestResponseBodyAdviceChain(advice);

		HttpInputMessage wrapped = new ServletServerHttpRequest(new MockHttpServletRequest());
		given(requestAdvice.supports(this.paramType, String.class, this.converterType)).willReturn(true);
		given(requestAdvice.beforeBodyRead(eq(this.request), eq(this.paramType), eq(String.class),
				eq(this.converterType))).willReturn(wrapped);

		assertSame(wrapped, chain.beforeBodyRead(this.request, this.paramType, String.class, this.converterType));

		String modified = "body++";
		given(requestAdvice.afterBodyRead(eq(this.body), eq(this.request), eq(this.paramType),
				eq(String.class), eq(this.converterType))).willReturn(modified);

		assertEquals(modified, chain.afterBodyRead(this.body, this.request, this.paramType,
				String.class, this.converterType));
	}
