	@Test
	public void dynamicTypeCheckboxAttribute() throws JspException {
		try {
			this.tag.setDynamicAttribute(null, "type", "checkbox");
			fail("Expected exception");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Attribute type=\"checkbox\" is not allowed", e.getMessage());
		}
	}
