	@Test
	public void defaultActionEncoded() throws Exception {

		this.request.setRequestURI("/a b c");
		request.setQueryString("");

		this.tag.doStartTag();
		this.tag.doEndTag();
		this.tag.doFinally();

		String output = getOutput();
		String formOutput = getFormTag(output);

		assertContainsAttribute(formOutput, "action", "/a%20b%20c");
	}
