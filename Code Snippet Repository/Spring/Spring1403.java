	@Before
	public void setUp() {
		beanFactory = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor aabpp = new AutowiredAnnotationBeanPostProcessor();
		aabpp.setBeanFactory(beanFactory);
		beanFactory.addBeanPostProcessor(aabpp);
		beanFactory.registerBeanDefinition("abstractBean", new RootBeanDefinition(AbstractBean.class));
		beanFactory.registerBeanDefinition("beanConsumer", new RootBeanDefinition(BeanConsumer.class));
		RootBeanDefinition tbd = new RootBeanDefinition(TestBean.class);
		tbd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		beanFactory.registerBeanDefinition("testBean", tbd);
	}
