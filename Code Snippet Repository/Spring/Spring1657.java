	@Test
	public void testDefaultAndNonDefaultProfile() {
		assertThat(beanFactoryFor(DEFAULT_ELIGIBLE_XML, NONE_ACTIVE), containsTargetBean());
		assertThat(beanFactoryFor(DEFAULT_ELIGIBLE_XML, "other"), not(containsTargetBean()));

		{
			DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
			XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
			ConfigurableEnvironment env = new StandardEnvironment();
			env.setActiveProfiles(DEV_ACTIVE);
			env.setDefaultProfiles("default");
			reader.setEnvironment(env);
			reader.loadBeanDefinitions(new ClassPathResource(DEFAULT_AND_DEV_ELIGIBLE_XML, getClass()));
			assertThat(beanFactory, containsTargetBean());
		}
		{
			DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
			XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
			ConfigurableEnvironment env = new StandardEnvironment();
			// env.setActiveProfiles(DEV_ACTIVE);
			env.setDefaultProfiles("default");
			reader.setEnvironment(env);
			reader.loadBeanDefinitions(new ClassPathResource(DEFAULT_AND_DEV_ELIGIBLE_XML, getClass()));
			assertThat(beanFactory, containsTargetBean());
		}
		{
			DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
			XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
			ConfigurableEnvironment env = new StandardEnvironment();
			// env.setActiveProfiles(DEV_ACTIVE);
			//env.setDefaultProfiles("default");
			reader.setEnvironment(env);
			reader.loadBeanDefinitions(new ClassPathResource(DEFAULT_AND_DEV_ELIGIBLE_XML, getClass()));
			assertThat(beanFactory, containsTargetBean());
		}
	}
