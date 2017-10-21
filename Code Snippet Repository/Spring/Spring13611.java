	@Test
	public void emptyItems() throws Exception {
		this.tag.setPath("country");
		this.tag.setItems(Collections.EMPTY_LIST);

		this.tag.setItemValue("isoCode");
		this.tag.setItemLabel("name");
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();
		assertEquals("<select id=\"country\" name=\"country\"></select>", output);
	}
