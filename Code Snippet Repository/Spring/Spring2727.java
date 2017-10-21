	@Before
	public void setUp() {
		ctx = new ClassPathXmlApplicationContext(getClass().getSimpleName() + ".xml", getClass());
		testBean1 = (ITestBean) ctx.getBean("testBean1");
		testBean2 = (ITestBean) ctx.getBean("testBean2");
		testBeanContainingNestedBean = (ITestBean) ctx.getBean("testBeanContainingNestedBean");
		testFactoryBean1 = (Map<?, ?>) ctx.getBean("testFactoryBean1");
		testFactoryBean2 = (Map<?, ?>) ctx.getBean("testFactoryBean2");
		counterAspect = (Counter) ctx.getBean("counterAspect");
		interceptThis = (ITestBean) ctx.getBean("interceptThis");
		dontInterceptThis = (ITestBean) ctx.getBean("dontInterceptThis");
		testInterceptor = (TestInterceptor) ctx.getBean("testInterceptor");

		counterAspect.reset();
	}
