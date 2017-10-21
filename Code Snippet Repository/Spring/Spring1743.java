	@Test
	public void testClasspathPathName() throws Exception {
		PropertyEditor pathEditor = new PathEditor();
		pathEditor.setAsText("classpath:" + ClassUtils.classPackageAsResourcePath(getClass()) + "/" +
				ClassUtils.getShortName(getClass()) + ".class");
		Object value = pathEditor.getValue();
		assertTrue(value instanceof Path);
		Path path = (Path) value;
		assertTrue(path.toFile().exists());
	}
