	@Test
	public void testFieldOfWrongTypeAnnotatedWithPersistenceUnit() {
		PersistenceAnnotationBeanPostProcessor pabpp = new PersistenceAnnotationBeanPostProcessor();
		try {
			pabpp.postProcessPropertyValues(null, null, new FieldOfWrongTypeAnnotatedWithPersistenceUnit(), "bean");
			fail("Can't inject this field");
		}
		catch (IllegalStateException ex) {
			// Ok
		}
	}
