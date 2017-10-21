	@Test
	public void withNestedOptions() throws Exception {
		this.tag.setPath("country");
		int result = this.tag.doStartTag();
		assertEquals(Tag.EVAL_BODY_INCLUDE, result);

		BindStatus value = (BindStatus) getPageContext().getAttribute(SelectTag.LIST_VALUE_PAGE_ATTRIBUTE);
		assertEquals("Selected country not exposed in page context", "UK", value.getValue());

		result = this.tag.doEndTag();
		assertEquals(Tag.EVAL_PAGE, result);
		this.tag.doFinally();

		String output = getOutput();
		assertTrue(output.startsWith("<select "));
		assertTrue(output.endsWith("</select>"));
		assertContainsAttribute(output, "name", "country");
	}
