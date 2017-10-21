	@Test
	public void testSunnyDay() throws Exception {
		InputStream stream = null;
		try {
			String resource = "classpath:" + ClassUtils.classPackageAsResourcePath(getClass()) +
					"/" + ClassUtils.getShortName(getClass()) + ".class";
			InputStreamEditor editor = new InputStreamEditor();
			editor.setAsText(resource);
			Object value = editor.getValue();
			assertNotNull(value);
			assertTrue(value instanceof InputStream);
			stream = (InputStream) value;
			assertTrue(stream.available() > 0);
		}
		finally {
			if (stream != null) {
				stream.close();
			}
		}
	}
