	@Before
	public void setUp() {
		ClassPathXmlApplicationContext ctx =
			new ClassPathXmlApplicationContext(getClass().getSimpleName() + ".xml", getClass());
		highPrecedenceAspect = (PrecedenceTestAspect) ctx.getBean("highPrecedenceAspect");
		lowPrecedenceAspect = (PrecedenceTestAspect) ctx.getBean("lowPrecedenceAspect");
		highPrecedenceSpringAdvice = (SimpleSpringBeforeAdvice) ctx.getBean("highPrecedenceSpringAdvice");
		lowPrecedenceSpringAdvice = (SimpleSpringBeforeAdvice) ctx.getBean("lowPrecedenceSpringAdvice");
		testBean = (ITestBean) ctx.getBean("testBean");
	}
