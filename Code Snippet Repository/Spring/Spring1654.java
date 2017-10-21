	@Test
	public void getBean_withActiveProfile() {
		ConfigurableEnvironment env = new StandardEnvironment();
		env.setActiveProfiles("dev");

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
		reader.setEnvironment(env);
		reader.loadBeanDefinitions(XML);

		bf.getBean("devOnlyBean"); // should not throw NSBDE

		Object foo = bf.getBean("foo");
		assertThat(foo, instanceOf(Integer.class));

		bf.getBean("devOnlyBean");
	}
