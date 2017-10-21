	@Test
	public void getCorsConfigWithBeanNameHandler() throws Exception {

		String key = "foo";
		String beanName = "handler1";

		StaticWebApplicationContext context = new StaticWebApplicationContext();
		context.registerSingleton(beanName, MyHandler.class);

		this.mapping.setApplicationContext(context);
		this.mapping.registerMapping(key, beanName, this.method1);
		HandlerMethod handlerMethod = this.mapping.getHandlerInternal(new MockHttpServletRequest("GET", key));

		CorsConfiguration config = this.mapping.getMappingRegistry().getCorsConfiguration(handlerMethod);
		assertNotNull(config);
		assertEquals("http://" + beanName.hashCode() + this.method1.getName(), config.getAllowedOrigins().get(0));
	}
