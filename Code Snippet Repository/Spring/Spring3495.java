	@Test
	public void testContextWithInvalidLazyClass() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(INVALID_CLASS_CONTEXT, getClass());
		assertTrue(ctx.containsBean("someMessageSource"));
		try {
			ctx.getBean("someMessageSource");
			fail("Should have thrown CannotLoadBeanClassException");
		}
		catch (CannotLoadBeanClassException ex) {
			assertTrue(ex.contains(ClassNotFoundException.class));
		}
		ctx.close();
	}
