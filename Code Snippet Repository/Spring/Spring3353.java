	@Test
	public void primaryMetadataIsPropagated() {
		@Configuration class Config {
			@Primary @Bean
			Object foo() { return null; }
		}

		assertTrue("primary metadata was not propagated",
				beanDef(Config.class).isPrimary());
	}
