	@Test
	public void customBind() throws Exception {
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(createTestBean(), "testBean");
		result.getPropertyAccessor().registerCustomEditor(Float.class, new SimpleFloatEditor());
		exposeBindingResult(result);
		this.tag.setPath("myFloat");
		assertEquals(Tag.SKIP_BODY, this.tag.doStartTag());
		String output = getOutput();
		assertContainsAttribute(output, "name", "myFloat");
		assertBlockTagContains(output, "12.34f");
	}
