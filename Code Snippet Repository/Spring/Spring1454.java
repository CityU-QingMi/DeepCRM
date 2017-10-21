	@Test
	public void testPropertyPlaceholderConfigurerWithUnresolvablePlaceholder() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class)
				.addPropertyValue("name", "${ref}").getBeanDefinition());

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();

		try {
			ppc.postProcessBeanFactory(factory);
			fail("Should have thrown BeanDefinitionStoreException");
		}
		catch (BeanDefinitionStoreException ex) {
			// expected
			assertTrue(ex.getMessage().contains("ref"));
		}
	}
