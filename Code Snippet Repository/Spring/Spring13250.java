	@Test
	public void checkServletContextResource() throws Exception {
		Resource classpathLocation = new ClassPathResource("test/", PathResourceResolver.class);
		MockServletContext context = new MockServletContext();

		ServletContextResource servletContextLocation = new ServletContextResource(context, "/webjars/");
		ServletContextResource resource = new ServletContextResource(context, "/webjars/webjar-foo/1.0/foo.js");

		assertFalse(this.resolver.checkResource(resource, classpathLocation));
		assertTrue(this.resolver.checkResource(resource, servletContextLocation));
	}
