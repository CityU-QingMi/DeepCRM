	@Test
	public void nullItems() throws Exception {
		this.tag.setPath("country");
		this.tag.setItems(null);

		this.tag.setItemValue("isoCode");
		this.tag.setItemLabel("name");
		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();
		assertEquals("<select id=\"country\" name=\"country\"></select>", output);
	}
