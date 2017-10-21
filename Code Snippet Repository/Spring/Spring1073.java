	@Test
	public void checkNotWritablePropertyHoldPossibleMatches() {
		TestBean target = new TestBean();
		try {
			BeanWrapper accessor = createAccessor(target);
			accessor.setPropertyValue("ag", "foobar");
			fail("Should throw exception on invalid property");
		}
		catch (NotWritablePropertyException ex) {
			// expected
			assertEquals(1, ex.getPossibleMatches().length);
			assertEquals("age", ex.getPossibleMatches()[0]);
		}
	}
