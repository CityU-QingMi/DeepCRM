	@Test
	public void withInvalidList() throws Exception {
		this.tag.setPath("country");
		this.tag.setItems(new TestBean());
		this.tag.setItemValue("isoCode");
		try {
			this.tag.doStartTag();
			fail("Must not be able to use a non-Collection typed value as the value of 'items'");
		}
		catch (JspException expected) {
			String message = expected.getMessage();
			assertTrue(message.contains("items"));
			assertTrue(message.contains("org.springframework.tests.sample.beans.TestBean"));
		}
	}
