	@Test
	public void testSetterOfWrongTypeAnnotatedWithPersistenceUnit() {
		PersistenceAnnotationBeanPostProcessor pabpp = new PersistenceAnnotationBeanPostProcessor();
		try {
			pabpp.postProcessPropertyValues(null, null, new SetterOfWrongTypeAnnotatedWithPersistenceUnit(), "bean");
			fail("Can't inject this setter");
		}
		catch (IllegalStateException ex) {
			// Ok
		}
	}
