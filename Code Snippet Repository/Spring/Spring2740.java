	@Test
	public void testAdrian() {
		ClassPathXmlApplicationContext ctx =
			new ClassPathXmlApplicationContext(getClass().getSimpleName() + "-context.xml", getClass());

		ITestBean adrian = (ITestBean) ctx.getBean("adrian");
		assertEquals(0, LazyTestBean.instantiations);
		assertNotNull(adrian);
		adrian.getAge();
		assertEquals(68, adrian.getAge());
		assertEquals(1, LazyTestBean.instantiations);
	}
