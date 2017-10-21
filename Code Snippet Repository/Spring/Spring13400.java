	@Test
	public void paramWithExplicitNullValue() throws JspException {
		tag.setName("name");
		tag.setValue(null);

		int action = tag.doEndTag();

		assertEquals(Tag.EVAL_PAGE, action);
		assertEquals("name", parent.getParam().getName());
		assertNull(parent.getParam().getValue());
	}
