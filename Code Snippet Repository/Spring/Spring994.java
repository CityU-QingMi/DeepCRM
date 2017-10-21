	@Test
	public void setPrimitiveArrayPropertyWithAutoGrow() throws Exception {
		PrimitiveArrayBean target = new PrimitiveArrayBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.setAutoGrowNestedPaths(true);

		accessor.setPropertyValue("array[0]", 1);
		assertEquals(1, target.getArray().length);

		accessor.setPropertyValue("array[2]", 3);
		assertEquals(3, target.getArray().length);
		assertTrue("correct values", target.getArray()[0] == 1 && target.getArray()[1] == 0 &&
				target.getArray()[2] == 3);
	}
