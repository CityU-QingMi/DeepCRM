	@Test
	public void setBooleanProperty() {
		BooleanTestBean target = new BooleanTestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);

		accessor.setPropertyValue("bool2", "true");
		assertTrue("Correct bool2 value", Boolean.TRUE.equals(accessor.getPropertyValue("bool2")));
		assertTrue("Correct bool2 value", target.getBool2());

		accessor.setPropertyValue("bool2", "false");
		assertTrue("Correct bool2 value", Boolean.FALSE.equals(accessor.getPropertyValue("bool2")));
		assertTrue("Correct bool2 value", !target.getBool2());
	}
