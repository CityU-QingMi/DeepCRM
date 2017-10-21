	@Test
	public void controllerAdvice() {

		Object adviceBean = new ControllerAdviceBean(new MyControllerAdvice());
		RequestResponseBodyAdviceChain chain = new RequestResponseBodyAdviceChain(Arrays.asList(adviceBean));

		String actual = (String) chain.beforeBodyWrite(this.body, this.returnType, this.contentType,
				this.converterType, this.request, this.response);

		assertEquals("body-MyControllerAdvice", actual);
	}
