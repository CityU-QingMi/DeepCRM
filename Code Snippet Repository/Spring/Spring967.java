	@Test
	public void testErrorMessageOfNestedProperty() {
		ITestBean target = new TestBean();
		ITestBean child = new DifferentTestBean();
		child.setName("test");
		target.setSpouse(child);
		AbstractPropertyAccessor accessor = createAccessor(target);
		try {
			accessor.getPropertyValue("spouse.bla");
		}
		catch (NotReadablePropertyException ex) {
			assertTrue(ex.getMessage().contains(TestBean.class.getName()));
		}
	}
