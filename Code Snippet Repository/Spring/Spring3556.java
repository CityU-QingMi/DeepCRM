	@Test(expected = BeanDefinitionStoreException.class)
	@SuppressWarnings("")
	public void nestedUnresolvablePlaceholder() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.registerBeanDefinition("testBean",
				genericBeanDefinition(TestBean.class)
						.addPropertyValue("name", "${my.name}")
						.getBeanDefinition());

		PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
		ppc.setProperties(new Properties() {{
			put("my.name", "${bogus}");
		}});
		ppc.postProcessBeanFactory(bf); // should throw
	}
