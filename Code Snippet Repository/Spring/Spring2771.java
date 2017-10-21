	@Test
	public void testAdviceInvokedCorrectly() throws Exception {
		CountingBeforeAdvice getAgeCounter = (CountingBeforeAdvice) this.context.getBean("getAgeCounter");
		CountingBeforeAdvice getNameCounter = (CountingBeforeAdvice) this.context.getBean("getNameCounter");

		ITestBean bean = getTestBean();

		assertEquals("Incorrect initial getAge count", 0, getAgeCounter.getCalls("getAge"));
		assertEquals("Incorrect initial getName count", 0, getNameCounter.getCalls("getName"));

		bean.getAge();

		assertEquals("Incorrect getAge count on getAge counter", 1, getAgeCounter.getCalls("getAge"));
		assertEquals("Incorrect getAge count on getName counter", 0, getNameCounter.getCalls("getAge"));

		bean.getName();

		assertEquals("Incorrect getName count on getName counter", 1, getNameCounter.getCalls("getName"));
		assertEquals("Incorrect getName count on getAge counter", 0, getAgeCounter.getCalls("getName"));
	}
