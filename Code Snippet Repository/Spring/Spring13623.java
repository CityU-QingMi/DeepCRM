	@Test
	public void simpleBindWithDynamicAttributes() throws Exception {
		String dynamicAttribute1 = "attr1";
		String dynamicAttribute2 = "attr2";

		this.tag.setPath("name");
		this.tag.setReadonly(true);
		this.tag.setDynamicAttribute(null, dynamicAttribute1, dynamicAttribute1);
		this.tag.setDynamicAttribute(null, dynamicAttribute2, dynamicAttribute2);

		assertEquals(Tag.SKIP_BODY, this.tag.doStartTag());
		String output = getOutput();
		assertContainsAttribute(output, "name", "name");
		assertContainsAttribute(output, "readonly", "readonly");
		assertContainsAttribute(output, dynamicAttribute1, dynamicAttribute1);
		assertContainsAttribute(output, dynamicAttribute2, dynamicAttribute2);
		assertBlockTagContains(output, "Rob");
	}
