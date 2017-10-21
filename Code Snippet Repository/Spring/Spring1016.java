	@Test
	public void setEmptyValueForPrimitiveProperty() {
		TestBean target = new TestBean();
		try {
			AbstractPropertyAccessor accessor = createAccessor(target);
			accessor.setPropertyValue("age", "");
			fail("Should throw exception on type mismatch");
		}
		catch (TypeMismatchException ex) {
			// expected
		}
		catch (Exception ex) {
			fail("Shouldn't throw exception other than Type mismatch");
		}
	}
