	@Test
	public void testSetterWithNoArgs() {
		PersistenceAnnotationBeanPostProcessor pabpp = new PersistenceAnnotationBeanPostProcessor();
		try {
			pabpp.postProcessPropertyValues(null, null, new SetterWithNoArgs(), "bean");
			fail("Can't inject this setter");
		}
		catch (IllegalStateException ex) {
			// Ok
		}
	}
