	@Test
	public void setPropertyValuesIgnoresInvalidNestedOnRequest() {
		ITestBean target = new TestBean();
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.addPropertyValue(new PropertyValue("name", "rod"));
		pvs.addPropertyValue(new PropertyValue("graceful.rubbish", "tony"));
		pvs.addPropertyValue(new PropertyValue("more.garbage", new Object()));
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.setPropertyValues(pvs, true);
		assertTrue("Set valid and ignored invalid", target.getName().equals("rod"));
		try {
			// Don't ignore: should fail
			accessor.setPropertyValues(pvs, false);
			fail("Shouldn't have ignored invalid updates");
		}
		catch (NotWritablePropertyException ex) {
			// OK: but which exception??
		}
	}
