	@Test
	public void requestDataValueProcessorHooks() throws Exception {
		String action = "/my/form?foo=bar";
		RequestDataValueProcessor processor = getMockRequestDataValueProcessor();
		given(processor.processAction(this.request, action, "post")).willReturn(action);
		given(processor.getExtraHiddenFields(this.request)).willReturn(Collections.singletonMap("key", "value"));

		this.tag.doStartTag();
		this.tag.doEndTag();
		this.tag.doFinally();

		String output = getOutput();

		assertEquals("<div>\n<input type=\"hidden\" name=\"key\" value=\"value\" />\n</div>", getInputTag(output));
		assertFormTagOpened(output);
		assertFormTagClosed(output);
	}
