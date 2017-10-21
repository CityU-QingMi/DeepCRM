	@Test
	public void testChangesOnEquals() throws Exception {
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.addPropertyValue(new PropertyValue("forname", "Tony"));
		pvs.addPropertyValue(new PropertyValue("surname", "Blair"));
		pvs.addPropertyValue(new PropertyValue("age", "50"));
		MutablePropertyValues pvs2 = pvs;
		PropertyValues changes = pvs2.changesSince(pvs);
		assertTrue("changes are empty", changes.getPropertyValues().length == 0);
	}
