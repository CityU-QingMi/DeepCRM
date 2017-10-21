	@Test
	public void setStringPropertyWithCustomEditor() throws Exception {
		TestBean target = new TestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.registerCustomEditor(String.class, "name", new PropertyEditorSupport() {
			@Override
			public void setValue(Object value) {
				if (value instanceof String[]) {
					setValue(StringUtils.arrayToDelimitedString(((String[]) value), "-"));
				}
				else {
					super.setValue(value != null ? value : "");
				}
			}
		});
		accessor.setPropertyValue("name", new String[] {});
		assertEquals("", target.getName());
		accessor.setPropertyValue("name", new String[] {"a1", "b2"});
		assertEquals("a1-b2", target.getName());
		accessor.setPropertyValue("name", null);
		assertEquals("", target.getName());
	}
