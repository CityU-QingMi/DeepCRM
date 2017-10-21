	@Test
	public void testCustomNumberEditorWithFrenchBigDecimal() throws Exception {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.FRENCH);
		NumberTestBean tb = new NumberTestBean();
		BeanWrapper bw = new BeanWrapperImpl(tb);
		bw.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, nf, true));
		bw.setPropertyValue("bigDecimal", "1000");
		assertEquals(1000.0f, tb.getBigDecimal().floatValue(), 0f);
		bw.setPropertyValue("bigDecimal", "1000,5");
		assertEquals(1000.5f, tb.getBigDecimal().floatValue(), 0f);
		bw.setPropertyValue("bigDecimal", "1 000,5");
		assertEquals(1000.5f, tb.getBigDecimal().floatValue(), 0f);
	}
