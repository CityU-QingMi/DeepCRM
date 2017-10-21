	private void testValidCopy(TestObject src, TestObject dest) {
		src.setName("freddie");
		src.setAge(15);
		src.setSpouse(new TestObject());
		assertFalse(src.getAge() == dest.getAge());

		ReflectionUtils.shallowCopyFieldState(src, dest);
		assertEquals(src.getAge(), dest.getAge());
		assertEquals(src.getSpouse(), dest.getSpouse());
	}
