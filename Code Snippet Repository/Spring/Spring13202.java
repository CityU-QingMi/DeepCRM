	@Test
	public void resolveArgument() throws Exception {
		this.servletRequest.setContextPath("/myapp");
		this.servletRequest.setServletPath("/main");
		this.servletRequest.setPathInfo("/accounts");

		Object actual = this.resolver.resolveArgument(this.builderParam, new ModelAndViewContainer(), this.webRequest, null);

		assertNotNull(actual);
		assertEquals(ServletUriComponentsBuilder.class, actual.getClass());
		assertEquals("http://localhost/myapp/main", ((ServletUriComponentsBuilder) actual).build().toUriString());
	}
