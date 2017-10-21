	@Test
	public void testCircularDependencyWithApplicationContext() {
		try {
			new AnnotationConfigApplicationContext(A.class, AStrich.class);
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getMessage().contains("Circular reference"));
		}
	}
