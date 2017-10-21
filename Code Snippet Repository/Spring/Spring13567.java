	@Test
	public void dynamicTypeAttribute() throws JspException {
		try {
			this.tag.setDynamicAttribute(null, "type", "email");
			fail("Expected exception");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Attribute type=\"email\" is not allowed", e.getMessage());
		}
	}
