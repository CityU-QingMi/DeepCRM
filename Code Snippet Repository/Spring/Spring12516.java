	@Test
	public void testClassPathXmlApplicationContext() throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"/org/springframework/web/context/WEB-INF/applicationContext.xml");
		assertTrue("Has father", context.containsBean("father"));
		assertTrue("Has rod", context.containsBean("rod"));
		assertFalse("Hasn't kerry", context.containsBean("kerry"));
		assertTrue("Doesn't have spouse", ((TestBean) context.getBean("rod")).getSpouse() == null);
		assertTrue("myinit not evaluated", "Roderick".equals(((TestBean) context.getBean("rod")).getName()));

		context = new ClassPathXmlApplicationContext(new String[] {
			"/org/springframework/web/context/WEB-INF/applicationContext.xml",
			"/org/springframework/web/context/WEB-INF/context-addition.xml" });
		assertTrue("Has father", context.containsBean("father"));
		assertTrue("Has rod", context.containsBean("rod"));
		assertTrue("Has kerry", context.containsBean("kerry"));
	}
