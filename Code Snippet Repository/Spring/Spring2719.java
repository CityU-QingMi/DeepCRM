	@Before
	public void setUp() {
		ClassPathXmlApplicationContext ctx =
			new ClassPathXmlApplicationContext(getClass().getSimpleName() + ".xml", getClass());

		testBean = (ITestBean) ctx.getBean("testBean");
		afterThrowingAdviceAspect = (AfterThrowingAdviceBindingTestAspect) ctx.getBean("testAspect");

		mockCollaborator = mock(AfterThrowingAdviceBindingCollaborator.class);
		afterThrowingAdviceAspect.setCollaborator(mockCollaborator);
	}
