	@Test
	public void testSunnyDay() throws Exception {
		Reader reader = null;
		try {
			String resource = "classpath:" + ClassUtils.classPackageAsResourcePath(getClass()) +
					"/" + ClassUtils.getShortName(getClass()) + ".class";
			ReaderEditor editor = new ReaderEditor();
			editor.setAsText(resource);
			Object value = editor.getValue();
			assertNotNull(value);
			assertTrue(value instanceof Reader);
			reader = (Reader) value;
			assertTrue(reader.ready());
		}
		finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
