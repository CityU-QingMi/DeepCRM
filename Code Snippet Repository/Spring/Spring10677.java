	@Test
	public void setValues() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		assertEquals(Arrays.asList("*"), config.getAllowedOrigins());
		config.addAllowedHeader("*");
		assertEquals(Arrays.asList("*"), config.getAllowedHeaders());
		config.addAllowedMethod("*");
		assertEquals(Arrays.asList("*"), config.getAllowedMethods());
		config.addExposedHeader("header1");
		config.addExposedHeader("header2");
		assertEquals(Arrays.asList("header1", "header2"), config.getExposedHeaders());
		config.setAllowCredentials(true);
		assertTrue(config.getAllowCredentials());
		config.setMaxAge(123L);
		assertEquals(new Long(123), config.getMaxAge());
	}
