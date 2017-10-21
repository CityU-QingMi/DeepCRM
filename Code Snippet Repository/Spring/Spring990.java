	@Test
	public void setStringArrayWithAutoGrow() throws Exception {
		StringArrayBean target = new StringArrayBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.setAutoGrowNestedPaths(true);

		accessor.setPropertyValue("array[0]", "Test0");
		assertEquals(1, target.getArray().length);

		accessor.setPropertyValue("array[2]", "Test2");
		assertEquals(3, target.getArray().length);
		assertTrue("correct values", target.getArray()[0].equals("Test0") && target.getArray()[1] == null &&
				target.getArray()[2].equals("Test2"));
	}
