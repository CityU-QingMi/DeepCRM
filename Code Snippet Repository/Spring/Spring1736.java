	@Test
	public void testDefaultBooleanEditorForPrimitiveType() {
		BooleanTestBean tb = new BooleanTestBean();
		BeanWrapper bw = new BeanWrapperImpl(tb);

		bw.setPropertyValue("bool1", "true");
		assertTrue("Correct bool1 value", Boolean.TRUE.equals(bw.getPropertyValue("bool1")));
		assertTrue("Correct bool1 value", tb.isBool1());

		bw.setPropertyValue("bool1", "false");
		assertTrue("Correct bool1 value", Boolean.FALSE.equals(bw.getPropertyValue("bool1")));
		assertTrue("Correct bool1 value", !tb.isBool1());

		bw.setPropertyValue("bool1", "  true  ");
		assertTrue("Correct bool1 value", tb.isBool1());

		bw.setPropertyValue("bool1", "  false  ");
		assertTrue("Correct bool1 value", !tb.isBool1());

		bw.setPropertyValue("bool1", "on");
		assertTrue("Correct bool1 value", tb.isBool1());

		bw.setPropertyValue("bool1", "off");
		assertTrue("Correct bool1 value", !tb.isBool1());

		bw.setPropertyValue("bool1", "yes");
		assertTrue("Correct bool1 value", tb.isBool1());

		bw.setPropertyValue("bool1", "no");
		assertTrue("Correct bool1 value", !tb.isBool1());

		bw.setPropertyValue("bool1", "1");
		assertTrue("Correct bool1 value", tb.isBool1());

		bw.setPropertyValue("bool1", "0");
		assertTrue("Correct bool1 value", !tb.isBool1());

		try {
			bw.setPropertyValue("bool1", "argh");
			fail("Should have thrown BeansException");
		}
		catch (BeansException ex) {
			// expected
		}
	}
