	@Test
	public void printFormattedScopedAttributeResult() throws Exception {
		PercentStyleFormatter formatter = new PercentStyleFormatter();
		tag.setExpression("bean.formattable");
		int action = tag.doStartTag();
		assertEquals(Tag.EVAL_BODY_INCLUDE, action);
		action = tag.doEndTag();
		assertEquals(Tag.EVAL_PAGE, action);
		assertEquals(formatter.print(new BigDecimal(".25"), Locale.getDefault()),
				((MockHttpServletResponse) context.getResponse()).getContentAsString());
	}
