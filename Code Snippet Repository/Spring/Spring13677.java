	@Test
	public void parentsAreAbstract() throws Exception {
		try {
			rb.resolveViewName("debug.Parent", Locale.ENGLISH);
			fail("Should have thrown BeanIsAbstractException");
		}
		catch (BeanIsAbstractException ex) {
			// expected
		}
		try {
			rb.resolveViewName("testParent", Locale.ENGLISH);
			fail("Should have thrown BeanIsAbstractException");
		}
		catch (BeanIsAbstractException ex) {
			// expected
		}
	}
