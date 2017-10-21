	@Test
	public void combineWithNullProperties() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		config.addAllowedHeader("header1");
		config.addExposedHeader("header3");
		config.addAllowedMethod(HttpMethod.GET.name());
		config.setMaxAge(123L);
		config.setAllowCredentials(true);
		CorsConfiguration other = new CorsConfiguration();
		config = config.combine(other);
		assertEquals(Arrays.asList("*"), config.getAllowedOrigins());
		assertEquals(Arrays.asList("header1"), config.getAllowedHeaders());
		assertEquals(Arrays.asList("header3"), config.getExposedHeaders());
		assertEquals(Arrays.asList(HttpMethod.GET.name()), config.getAllowedMethods());
		assertEquals(new Long(123), config.getMaxAge());
		assertTrue(config.getAllowCredentials());
	}
