	@Test
	public void resolveUrlPath() throws Exception {
		this.request.setRequestURI("/context/servlet/resources/main.css");
		this.request.setContextPath("/context");
		this.request.setServletPath("/servlet");
		String resourcePath = "/context/servlet/resources/bar.css";
		Resource css = new ClassPathResource("test/main.css", getClass());
		String actual = this.transformer.resolveUrlPath(resourcePath, this.request, css, this.transformerChain);
		assertEquals("/context/servlet/resources/bar-11e16cf79faee7ac698c805cf28248d2.css", actual);
	}
