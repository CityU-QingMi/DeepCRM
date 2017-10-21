	@Test
	public void testPerTargetAspect() throws SecurityException, NoSuchMethodException {
		ClassPathXmlApplicationContext bf = newContext("pertarget.xml");

		ITestBean adrian1 = (ITestBean) bf.getBean("adrian");
		assertTrue(AopUtils.isAopProxy(adrian1));

		// Does not trigger advice or count
		int explicitlySetAge = 25;
		adrian1.setAge(explicitlySetAge);

		assertEquals("Setter does not initiate advice", explicitlySetAge, adrian1.getAge());
		// Fire aspect

		AspectMetadata am = new AspectMetadata(PerTargetAspect.class, "someBean");
		assertTrue(am.getPerClausePointcut().getMethodMatcher().matches(TestBean.class.getMethod("getSpouse"), null));

		adrian1.getSpouse();

		assertEquals("Advice has now been instantiated", 0, adrian1.getAge());
		adrian1.setAge(11);
		assertEquals("Any int setter increments", 2, adrian1.getAge());
		adrian1.setName("Adrian");
		//assertEquals("Any other setter does not increment", 2, adrian1.getAge());

		ITestBean adrian2 = (ITestBean) bf.getBean("adrian");
		assertNotSame(adrian1, adrian2);
		assertTrue(AopUtils.isAopProxy(adrian1));
		assertEquals(34, adrian2.getAge());
		adrian2.getSpouse();
		assertEquals("Aspect now fired", 0, adrian2.getAge());
		assertEquals(1, adrian2.getAge());
		assertEquals(2, adrian2.getAge());
		assertEquals(3, adrian1.getAge());
	}
