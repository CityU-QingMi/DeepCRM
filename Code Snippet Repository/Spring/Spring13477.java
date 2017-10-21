	@Test
	public void withoutErrorsInstance() throws Exception {
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		result = this.tag.doEndTag();
		assertEquals(Tag.EVAL_PAGE, result);

		String output = getOutput();
		assertEquals(0, output.length());
	}
