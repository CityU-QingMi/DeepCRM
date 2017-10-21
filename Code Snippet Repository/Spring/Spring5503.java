	@Test
	public void validCopyOnSubTypeWithNewField() {
		TestObjectSubclassWithNewField src = new TestObjectSubclassWithNewField();
		TestObjectSubclassWithNewField dest = new TestObjectSubclassWithNewField();
		src.magic = 11;

		// Will check inherited fields are copied
		testValidCopy(src, dest);

		// Check subclass fields were copied
		assertEquals(src.magic, dest.magic);
		assertEquals(src.prot, dest.prot);
	}
