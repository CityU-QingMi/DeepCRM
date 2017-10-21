	@Test
	public void setValueAsCustomObjectInvokesToString() throws Exception {
		final String expectedValue = "'Green Wing' - classic British comedy";
		Object object = new Object() {
			@Override
			public String toString() {
				return expectedValue;
			}
		};

		editor.setValue(object);
		assertEquals(expectedValue, editor.getAsText());
	}
