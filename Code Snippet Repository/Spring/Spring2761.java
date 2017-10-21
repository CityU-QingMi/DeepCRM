	@Before
	public void setUp() {
		ClassPathXmlApplicationContext ctx =
			new ClassPathXmlApplicationContext(getClass().getSimpleName() + "-context.xml", getClass());

		counterAspect = (CounterAspect) ctx.getBean("counterAspect");
		counterAspect.reset();

		testBean = (GenericReturnTypeVariationClass) ctx.getBean("testBean");
	}
