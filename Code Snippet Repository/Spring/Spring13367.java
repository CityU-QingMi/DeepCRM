	@Test
	public void setFormattedScopedAttributeResult() throws Exception {
		tag.setExpression("bean.formattable");
		tag.setVar("foo");
		int action = tag.doStartTag();
		assertEquals(Tag.EVAL_BODY_INCLUDE, action);
		action = tag.doEndTag();
		assertEquals(Tag.EVAL_PAGE, action);
		assertEquals(new BigDecimal(".25"), context.getAttribute("foo"));
	}
