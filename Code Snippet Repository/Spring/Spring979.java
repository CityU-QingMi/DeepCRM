	@Test
	public void setPropertyWithCustomEditor() {
		MutablePropertyValues values = new MutablePropertyValues();
		values.add("name", Integer.class);
		TestBean target = new TestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setValue(Object value) {
				super.setValue(value.toString());
			}
		});
		accessor.setPropertyValues(values);
		assertEquals(Integer.class.toString(), target.getName());
	}
