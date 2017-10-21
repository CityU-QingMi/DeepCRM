	@SuppressWarnings("")
	@org.junit.Before
	public void setUp() {
		ClassPathXmlApplicationContext ctx =
			new ClassPathXmlApplicationContext(getClass().getSimpleName() + "-context.xml", getClass());

		counterAspect = (GenericCounterAspect) ctx.getBean("counterAspect");
		counterAspect.count = 0;

		testBean = (DerivedInterface<String>) ctx.getBean("testBean");
	}
