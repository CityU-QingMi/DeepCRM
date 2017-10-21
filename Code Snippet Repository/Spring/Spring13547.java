	@Test
	public void withoutContext() throws Exception {
		this.tag.setParent(null);
		this.tag.setValue("foo");
		this.tag.setLabel("Foo");
		try {
			tag.doStartTag();
			fail("Must not be able to use <option> tag without exposed context.");
		}
		catch (IllegalStateException ex) {
			// expected
		}
	}
