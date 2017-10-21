	@Before
	public void setUp() {
		p1BeanDef = rootBeanDefinition(TestBean.class)
				.addPropertyValue("name", "${" + P1 + "}")
				.getBeanDefinition();

		bf = new DefaultListableBeanFactory();

		ppcProperties = new Properties();
		ppcProperties.setProperty(P1, P1_LOCAL_PROPS_VAL);
		System.setProperty(P1, P1_SYSTEM_PROPS_VAL);
		getModifiableSystemEnvironment().put(P1, P1_SYSTEM_ENV_VAL);
		ppc = new PropertyPlaceholderConfigurer();
		ppc.setProperties(ppcProperties);

	}
