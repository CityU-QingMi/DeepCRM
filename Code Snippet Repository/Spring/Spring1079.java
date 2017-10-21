	@Test
	public void incompletelyQuotedKeyLeadsToPropertyException() {
		TestBean target = new TestBean();
		try {
			BeanWrapper accessor = createAccessor(target);
			accessor.setPropertyValue("[']", "foobar");
			fail("Should throw exception on invalid property");
		}
		catch (NotWritablePropertyException ex) {
			assertNull(ex.getPossibleMatches());
		}
	}
