	private void testMetaClass(String xmlFile) {
		// expect the exception we threw in the custom metaclass to show it got invoked
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext(xmlFile);
			Calculator calc = (Calculator) ctx.getBean("delegatingCalculator");
			calc.add(1, 2);
			fail("expected IllegalStateException");
		}
		catch (IllegalStateException ex) {
			assertEquals("Gotcha", ex.getMessage());
		}
	}
