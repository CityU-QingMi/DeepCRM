	@Test
	public void testCustomBooleanEditorWithAllowEmpty() {
		BooleanTestBean tb = new BooleanTestBean();
		BeanWrapper bw = new BeanWrapperImpl(tb);
		bw.registerCustomEditor(Boolean.class, new CustomBooleanEditor(true));

		bw.setPropertyValue("bool2", "true");
		assertTrue("Correct bool2 value", Boolean.TRUE.equals(bw.getPropertyValue("bool2")));
		assertTrue("Correct bool2 value", tb.getBool2().booleanValue());

		bw.setPropertyValue("bool2", "false");
		assertTrue("Correct bool2 value", Boolean.FALSE.equals(bw.getPropertyValue("bool2")));
		assertTrue("Correct bool2 value", !tb.getBool2().booleanValue());

		bw.setPropertyValue("bool2", "on");
		assertTrue("Correct bool2 value", tb.getBool2().booleanValue());

		bw.setPropertyValue("bool2", "off");
		assertTrue("Correct bool2 value", !tb.getBool2().booleanValue());

		bw.setPropertyValue("bool2", "yes");
		assertTrue("Correct bool2 value", tb.getBool2().booleanValue());

		bw.setPropertyValue("bool2", "no");
		assertTrue("Correct bool2 value", !tb.getBool2().booleanValue());

		bw.setPropertyValue("bool2", "1");
		assertTrue("Correct bool2 value", tb.getBool2().booleanValue());

		bw.setPropertyValue("bool2", "0");
		assertTrue("Correct bool2 value", !tb.getBool2().booleanValue());

		bw.setPropertyValue("bool2", "");
		assertTrue("Correct bool2 value", bw.getPropertyValue("bool2") == null);
		assertTrue("Correct bool2 value", tb.getBool2() == null);
	}
