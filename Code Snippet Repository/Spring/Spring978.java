	@Test
	public void setPropertyToNull() {
		TestBean target = new TestBean();
		target.setName("Frank");    // we need to change it back
		target.setSpouse(target);
		AbstractPropertyAccessor accessor = createAccessor(target);
		assertTrue("name is not null to start off", target.getName() != null);
		accessor.setPropertyValue("name", null);
		assertTrue("name is now null", target.getName() == null);
		// now test with non-string
		assertTrue("spouse is not null to start off", target.getSpouse() != null);
		accessor.setPropertyValue("spouse", null);
		assertTrue("spouse is now null", target.getSpouse() == null);
	}
