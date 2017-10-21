	@Test
	public void testJspOnly() throws Exception {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/", ".jsp");

		standaloneSetup(new PersonController()).setViewResolvers(viewResolver).build()
			.perform(get("/person/Corea"))
				.andExpect(status().isOk())
				.andExpect(model().size(1))
				.andExpect(model().attributeExists("person"))
				.andExpect(forwardedUrl("/WEB-INF/person/show.jsp"));
	}
