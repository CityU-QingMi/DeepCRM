	private void doTestConversionServiceInApplicationContext(String fileName, Class<?> resourceClass) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(fileName, getClass());
		ResourceTestBean tb = ctx.getBean("resourceTestBean", ResourceTestBean.class);
		assertTrue(resourceClass.isInstance(tb.getResource()));
		assertTrue(tb.getResourceArray().length > 0);
		assertTrue(resourceClass.isInstance(tb.getResourceArray()[0]));
		assertTrue(tb.getResourceMap().size() == 1);
		assertTrue(resourceClass.isInstance(tb.getResourceMap().get("key1")));
		assertTrue(tb.getResourceArrayMap().size() == 1);
		assertTrue(tb.getResourceArrayMap().get("key1").length > 0);
		assertTrue(resourceClass.isInstance(tb.getResourceArrayMap().get("key1")[0]));
	}
