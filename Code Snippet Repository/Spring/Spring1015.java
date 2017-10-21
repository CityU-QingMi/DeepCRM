	@Test
	public void setPropertyTypeMismatch() {
		TestBean target = new TestBean();
		try {
			AbstractPropertyAccessor accessor = createAccessor(target);
			accessor.setPropertyValue("age", "foobar");
			fail("Should throw exception on type mismatch");
		}
		catch (TypeMismatchException ex) {
			// expected
		}
	}
