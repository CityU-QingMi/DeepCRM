	@Test
	public void testCustomNumberEditorWithAllowEmpty() {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);
		NumberTestBean tb = new NumberTestBean();
		BeanWrapper bw = new BeanWrapperImpl(tb);
		bw.registerCustomEditor(long.class, new CustomNumberEditor(Long.class, nf, true));
		bw.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, nf, true));

		bw.setPropertyValue("long1", "5");
		bw.setPropertyValue("long2", "6");
		assertTrue("Correct long1 value", new Long("5").equals(bw.getPropertyValue("long1")));
		assertTrue("Correct long1 value", tb.getLong1() == 5);
		assertTrue("Correct long2 value", new Long("6").equals(bw.getPropertyValue("long2")));
		assertTrue("Correct long2 value", new Long("6").equals(tb.getLong2()));

		bw.setPropertyValue("long2", "");
		assertTrue("Correct long2 value", bw.getPropertyValue("long2") == null);
		assertTrue("Correct long2 value", tb.getLong2() == null);

		try {
			bw.setPropertyValue("long1", "");
			fail("Should have thrown BeansException");
		}
		catch (BeansException ex) {
			// expected
			assertTrue("Correct long1 value", new Long("5").equals(bw.getPropertyValue("long1")));
			assertTrue("Correct long1 value", tb.getLong1() == 5);
		}
	}
