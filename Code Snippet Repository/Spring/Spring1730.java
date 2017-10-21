	@Test
	public void testClassArrayEditorSunnyDayWithArrayTypes() throws Exception {
		ClassArrayEditor classArrayEditor = new ClassArrayEditor();
		classArrayEditor.setAsText("java.lang.String[],java.util.Map[],int[],float[][][]");
		Class<?>[] classes = (Class<?>[]) classArrayEditor.getValue();
		assertEquals(4, classes.length);
		assertEquals(String[].class, classes[0]);
		assertEquals(Map[].class, classes[1]);
		assertEquals(int[].class, classes[2]);
		assertEquals(float[][][].class, classes[3]);
		assertEquals("java.lang.String[],java.util.Map[],int[],float[][][]", classArrayEditor.getAsText());
		// ensure setAsText can consume the return value of getAsText
		classArrayEditor.setAsText(classArrayEditor.getAsText());
	}
