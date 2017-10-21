	@Test
	public void setAnotherPropertyIntermediatePropertyIsNull() throws Exception {
		ITestBean target = new TestBean("rod", 31);
		AbstractPropertyAccessor accessor = createAccessor(target);
		try {
			accessor.setPropertyValue("spouse.age", new Integer(31));
			fail("Shouldn't have succeeded with null path");
		}
		catch (NullValueInNestedPathException ex) {
			// expected
			assertTrue("it was the spouse property that was null, not " + ex.getPropertyName(),
					ex.getPropertyName().equals("spouse"));
		}
	}
