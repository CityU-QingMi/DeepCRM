	@Before
	public void setUp() throws Exception {
		ClassPathXmlApplicationContext ctx =
			new ClassPathXmlApplicationContext(getClass().getSimpleName() + ".xml", getClass());

		afterAdviceAspect = (AfterReturningAdviceBindingTestAspect) ctx.getBean("testAspect");

		mockCollaborator = mock(AfterReturningAdviceBindingCollaborator.class);
		afterAdviceAspect.setCollaborator(mockCollaborator);

		testBeanProxy = (ITestBean) ctx.getBean("testBean");
		assertTrue(AopUtils.isAopProxy(testBeanProxy));

		// we need the real target too, not just the proxy...
		this.testBeanTarget = (TestBean) ((Advised)testBeanProxy).getTargetSource().getTarget();
	}
