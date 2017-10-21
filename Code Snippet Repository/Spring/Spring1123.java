	@Test
	public void testAddOrOverride() throws Exception {
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.addPropertyValue(new PropertyValue("forname", "Tony"));
		pvs.addPropertyValue(new PropertyValue("surname", "Blair"));
		pvs.addPropertyValue(new PropertyValue("age", "50"));
		doTestTony(pvs);
		PropertyValue addedPv = new PropertyValue("rod", "Rod");
		pvs.addPropertyValue(addedPv);
		assertTrue(pvs.getPropertyValue("rod").equals(addedPv));
		PropertyValue changedPv = new PropertyValue("forname", "Greg");
		pvs.addPropertyValue(changedPv);
		assertTrue(pvs.getPropertyValue("forname").equals(changedPv));
	}
