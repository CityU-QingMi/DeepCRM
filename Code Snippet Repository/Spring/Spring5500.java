	@Test
	public void setField() {
		TestObjectSubclassWithNewField testBean = new TestObjectSubclassWithNewField();
		Field field = ReflectionUtils.findField(TestObjectSubclassWithNewField.class, "name", String.class);

		ReflectionUtils.makeAccessible(field);

		ReflectionUtils.setField(field, testBean, "FooBar");
		assertNotNull(testBean.getName());
		assertEquals("FooBar", testBean.getName());

		ReflectionUtils.setField(field, testBean, null);
		assertNull(testBean.getName());
	}
