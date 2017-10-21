	@Test
	public void lazyMetadataIsPropagated() {
		@Configuration class Config {
			@Lazy @Bean
			Object foo() { return null; }
		}

		assertTrue("lazy metadata was not propagated",
				beanDef(Config.class).isLazyInit());
	}
