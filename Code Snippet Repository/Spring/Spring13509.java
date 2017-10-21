	@Test
	public void disabledFalse() throws Exception {
		this.tag.setDisabled(false);

		this.tag.doStartTag();
		this.tag.doEndTag();

		String output = getOutput();
		assertTagOpened(output);
		assertTagClosed(output);

		assertAttributeNotPresent(output, "disabled");
	}
