	@Test
	public void testAdviceUsingJoinPoint() {
		ClassPathXmlApplicationContext bf = newContext("usesJoinPointAspect.xml");

		ITestBean adrian1 = (ITestBean) bf.getBean("adrian");
		adrian1.getAge();
		AdviceUsingThisJoinPoint aspectInstance = (AdviceUsingThisJoinPoint) bf.getBean("aspect");
		//(AdviceUsingThisJoinPoint) Aspects.aspectOf(AdviceUsingThisJoinPoint.class);
		//assertEquals("method-execution(int TestBean.getAge())",aspectInstance.getLastMethodEntered());
		assertTrue(aspectInstance.getLastMethodEntered().indexOf("TestBean.getAge())") != 0);
	}
