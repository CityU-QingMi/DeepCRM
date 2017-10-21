	@Test
	public void testArgNamesError() {
		try {
			new ClassPathXmlApplicationContext(getClass().getSimpleName() + "-error.xml", getClass());
			fail("Expected BeanCreationException");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.contains(IllegalArgumentException.class));
		}
	}
