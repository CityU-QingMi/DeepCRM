	@Test
	public void controllerAdviceNotApplicable() {

		Object adviceBean = new ControllerAdviceBean(new TargetedControllerAdvice());
		RequestResponseBodyAdviceChain chain = new RequestResponseBodyAdviceChain(Arrays.asList(adviceBean));

		String actual = (String) chain.beforeBodyWrite(this.body, this.returnType, this.contentType,
				this.converterType, this.request, this.response);

		assertEquals(this.body, actual);
	}
