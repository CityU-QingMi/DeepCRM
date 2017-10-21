	@Test
	public void testClassArrayEditorSunnyDay() throws Exception {
		ClassArrayEditor classArrayEditor = new ClassArrayEditor();
		classArrayEditor.setAsText("java.lang.String,java.util.HashMap");
		Class<?>[] classes = (Class<?>[]) classArrayEditor.getValue();
		assertEquals(2, classes.length);
		assertEquals(String.class, classes[0]);
		assertEquals(HashMap.class, classes[1]);
		assertEquals("java.lang.String,java.util.HashMap", classArrayEditor.getAsText());
		// ensure setAsText can consume the return value of getAsText
		classArrayEditor.setAsText(classArrayEditor.getAsText());
	}
