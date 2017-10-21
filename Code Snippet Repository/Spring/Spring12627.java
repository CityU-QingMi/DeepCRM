	@Test
	public void testCorsMinimal() throws Exception {
		loadBeanDefinitions("mvc-config-cors-minimal.xml");

		String[] beanNames = appContext.getBeanNamesForType(AbstractHandlerMapping.class);
		assertEquals(2, beanNames.length);
		for (String beanName : beanNames) {
			AbstractHandlerMapping handlerMapping = (AbstractHandlerMapping)appContext.getBean(beanName);
			assertNotNull(handlerMapping);
			Map<String, CorsConfiguration> configs = handlerMapping.getCorsConfigurations();
			assertNotNull(configs);
			assertEquals(1, configs.size());
			CorsConfiguration config = configs.get("/**");
			assertNotNull(config);
			assertArrayEquals(new String[]{"*"}, config.getAllowedOrigins().toArray());
			assertArrayEquals(new String[]{"GET", "HEAD", "POST"}, config.getAllowedMethods().toArray());
			assertArrayEquals(new String[]{"*"}, config.getAllowedHeaders().toArray());
			assertNull(config.getExposedHeaders());
			assertTrue(config.getAllowCredentials());
			assertEquals(Long.valueOf(1800), config.getMaxAge());
		}
	}
