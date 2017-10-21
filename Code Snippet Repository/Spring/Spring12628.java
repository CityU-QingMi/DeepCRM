	@Test
	public void testCors() throws Exception {
		loadBeanDefinitions("mvc-config-cors.xml");

		String[] beanNames = appContext.getBeanNamesForType(AbstractHandlerMapping.class);
		assertEquals(2, beanNames.length);
		for (String beanName : beanNames) {
			AbstractHandlerMapping handlerMapping = (AbstractHandlerMapping)appContext.getBean(beanName);
			assertNotNull(handlerMapping);
			Map<String, CorsConfiguration> configs = handlerMapping.getCorsConfigurations();
			assertNotNull(configs);
			assertEquals(2, configs.size());
			CorsConfiguration config = configs.get("/api/**");
			assertNotNull(config);
			assertArrayEquals(new String[]{"http://domain1.com", "http://domain2.com"}, config.getAllowedOrigins().toArray());
			assertArrayEquals(new String[]{"GET", "PUT"}, config.getAllowedMethods().toArray());
			assertArrayEquals(new String[]{"header1", "header2", "header3"}, config.getAllowedHeaders().toArray());
			assertArrayEquals(new String[]{"header1", "header2"}, config.getExposedHeaders().toArray());
			assertFalse(config.getAllowCredentials());
			assertEquals(Long.valueOf(123), config.getMaxAge());
			config = configs.get("/resources/**");
			assertArrayEquals(new String[]{"http://domain1.com"}, config.getAllowedOrigins().toArray());
			assertArrayEquals(new String[]{"GET", "HEAD", "POST"}, config.getAllowedMethods().toArray());
			assertArrayEquals(new String[]{"*"}, config.getAllowedHeaders().toArray());
			assertNull(config.getExposedHeaders());
			assertTrue(config.getAllowCredentials());
			assertEquals(Long.valueOf(1800), config.getMaxAge());
		}
	}
