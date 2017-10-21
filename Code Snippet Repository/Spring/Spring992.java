	@Test
	public void setPrimitiveArrayPropertyLargeMatchingWithSpecificEditor() {
		PrimitiveArrayBean target = new PrimitiveArrayBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.registerCustomEditor(int.class, "array", new PropertyEditorSupport() {
			@Override
			public void setValue(Object value) {
				if (value instanceof Integer) {
					super.setValue(new Integer((Integer) value + 1));
				}
			}
		});
		int[] input = new int[1024];
		accessor.setPropertyValue("array", input);
		assertEquals(1024, target.getArray().length);
		assertEquals(1, target.getArray()[0]);
		assertEquals(1, target.getArray()[1]);
	}
