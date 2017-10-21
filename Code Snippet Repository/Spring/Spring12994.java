	@SuppressWarnings("")
	@Test
	public void responseBodyAdvice() {

		RequestBodyAdvice requestAdvice = Mockito.mock(RequestBodyAdvice.class);
		ResponseBodyAdvice<String> responseAdvice = Mockito.mock(ResponseBodyAdvice.class);
		List<Object> advice = Arrays.asList(requestAdvice, responseAdvice);
		RequestResponseBodyAdviceChain chain = new RequestResponseBodyAdviceChain(advice);

		String expected = "body++";
		given(responseAdvice.supports(this.returnType, this.converterType)).willReturn(true);
		given(responseAdvice.beforeBodyWrite(eq(this.body), eq(this.returnType), eq(this.contentType),
				eq(this.converterType), same(this.request), same(this.response))).willReturn(expected);

		String actual = (String) chain.beforeBodyWrite(this.body, this.returnType, this.contentType,
				this.converterType, this.request, this.response);

		assertEquals(expected, actual);
	}
