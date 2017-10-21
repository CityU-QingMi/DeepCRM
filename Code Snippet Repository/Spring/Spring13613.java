	@Test
	public void withListAndTransformTag() throws Exception {
		this.tag.setPath("country");
		this.tag.setItems(Country.getCountries());
		assertList(true);

		TransformTag transformTag = new TransformTag();
		transformTag.setValue(Country.getCountries().get(0));
		transformTag.setVar("key");
		transformTag.setParent(this.tag);
		transformTag.setPageContext(getPageContext());
		transformTag.doStartTag();
		assertEquals("Austria(AT)", getPageContext().findAttribute("key"));
	}
