	@Before
	public void onSetUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext(getClass().getSimpleName() + ".xml", getClass());

		AroundAdviceBindingTestAspect  aroundAdviceAspect = ((AroundAdviceBindingTestAspect) ctx.getBean("testAspect"));

		ITestBean injectedTestBean = (ITestBean) ctx.getBean("testBean");
		assertTrue(AopUtils.isAopProxy(injectedTestBean));

		this.testBeanProxy = injectedTestBean;
		// we need the real target too, not just the proxy...

		this.testBeanTarget = (TestBean) ((Advised) testBeanProxy).getTargetSource().getTarget();

		mockCollaborator = mock(AroundAdviceBindingCollaborator.class);
		aroundAdviceAspect.setCollaborator(mockCollaborator);
	}
