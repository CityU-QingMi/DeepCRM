	@Test
	public void testClassEditor() {
		PropertyEditor classEditor = new ClassEditor();
		classEditor.setAsText(TestBean.class.getName());
		assertEquals(TestBean.class, classEditor.getValue());
		assertEquals(TestBean.class.getName(), classEditor.getAsText());

		classEditor.setAsText(null);
		assertEquals("", classEditor.getAsText());
		classEditor.setAsText("");
		assertEquals("", classEditor.getAsText());
		classEditor.setAsText("\t  ");
		assertEquals("", classEditor.getAsText());
	}
