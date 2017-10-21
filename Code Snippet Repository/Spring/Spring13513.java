	@Test
	public void dynamicTypeRadioAttribute() throws JspException {
		try {
			this.tag.setDynamicAttribute(null, "type", "radio");
			fail("Expected exception");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Attribute type=\"radio\" is not allowed", e.getMessage());
		}
	}
