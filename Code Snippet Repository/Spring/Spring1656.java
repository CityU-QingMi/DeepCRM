	@Test
	public void testDefaultProfile() {
		{
			DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
			XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
			ConfigurableEnvironment env = new StandardEnvironment();
			env.setDefaultProfiles("custom-default");
			reader.setEnvironment(env);
			reader.loadBeanDefinitions(new ClassPathResource(DEFAULT_ELIGIBLE_XML, getClass()));

			assertThat(beanFactory, not(containsTargetBean()));
		}
		{
			DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
			XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
			ConfigurableEnvironment env = new StandardEnvironment();
			env.setDefaultProfiles("custom-default");
			reader.setEnvironment(env);
			reader.loadBeanDefinitions(new ClassPathResource(CUSTOM_DEFAULT_ELIGIBLE_XML, getClass()));

			assertThat(beanFactory, containsTargetBean());
		}
	}
