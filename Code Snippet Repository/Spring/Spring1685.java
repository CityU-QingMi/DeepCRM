	@Before
	public void setUp() {
		parent = new DefaultListableBeanFactory();
		Map m = new HashMap();
		m.put("name", "Albert");
		RootBeanDefinition bd1 = new RootBeanDefinition(TestBean.class);
		bd1.setPropertyValues(new MutablePropertyValues(m));
		parent.registerBeanDefinition("father", bd1);
		m = new HashMap();
		m.put("name", "Roderick");
		RootBeanDefinition bd2 = new RootBeanDefinition(TestBean.class);
		bd2.setPropertyValues(new MutablePropertyValues(m));
		parent.registerBeanDefinition("rod", bd2);

		this.factory = new DefaultListableBeanFactory(parent);
		new XmlBeanDefinitionReader(this.factory).loadBeanDefinitions(
				new ClassPathResource("test.xml", getClass()));
		this.factory.addBeanPostProcessor(new BeanPostProcessor() {
			@Override
			public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
				if (bean instanceof TestBean) {
					((TestBean) bean).setPostProcessed(true);
				}
				if (bean instanceof DummyFactory) {
					((DummyFactory) bean).setPostProcessed(true);
				}
				return bean;
			}
			@Override
			public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
				return bean;
			}
		});
		this.factory.addBeanPostProcessor(new LifecycleBean.PostProcessor());
		this.factory.addBeanPostProcessor(new ProtectedLifecycleBean.PostProcessor());
		//this.factory.preInstantiateSingletons();
	}
