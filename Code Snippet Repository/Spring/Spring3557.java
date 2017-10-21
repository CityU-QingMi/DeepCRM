	@Test
	@SuppressWarnings("")
	public void ignoredNestedUnresolvablePlaceholder() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.registerBeanDefinition("testBean",
				genericBeanDefinition(TestBean.class)
						.addPropertyValue("name", "${my.name}")
						.getBeanDefinition());

		PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
		ppc.setProperties(new Properties() {{
			put("my.name", "${bogus}");
		}});
		ppc.setIgnoreUnresolvablePlaceholders(true);
		ppc.postProcessBeanFactory(bf);
		assertThat(bf.getBean(TestBean.class).getName(), equalTo("${bogus}"));
	}
