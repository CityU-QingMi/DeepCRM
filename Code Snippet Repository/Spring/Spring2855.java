	@Test
	public void testCanAddThrowsAdviceWithoutAdvisor() throws Throwable {
		DefaultListableBeanFactory f = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(f).loadBeanDefinitions(new ClassPathResource(THROWS_ADVICE_CONTEXT, CLASS));
		MyThrowsHandler th = (MyThrowsHandler) f.getBean("throwsAdvice");
		CountingBeforeAdvice cba = (CountingBeforeAdvice) f.getBean("countingBeforeAdvice");
		assertEquals(0, cba.getCalls());
		assertEquals(0, th.getCalls());
		IEcho echo = (IEcho) f.getBean("throwsAdvised");
		int i = 12;
		echo.setA(i);
		assertEquals(i, echo.getA());
		assertEquals(2, cba.getCalls());
		assertEquals(0, th.getCalls());
		Exception expected = new Exception();
		try {
			echo.echoException(1, expected);
			fail();
		}
		catch (Exception ex) {
			assertEquals(expected, ex);
		}
		// No throws handler method: count should still be 0
		assertEquals(0, th.getCalls());

		// Handler knows how to handle this exception
		expected = new FileNotFoundException();
		try {
			echo.echoException(1, expected);
			fail();
		}
		catch (IOException ex) {
			assertEquals(expected, ex);
		}
		// One match
		assertEquals(1, th.getCalls("ioException"));
	}
