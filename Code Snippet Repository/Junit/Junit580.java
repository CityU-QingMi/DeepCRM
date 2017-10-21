	@Test
	void convertsStringsToPrimitiveTypes() {
		assertConverts("true", boolean.class, true);
		assertConverts("1", byte.class, (byte) 1);
		assertConverts("o", char.class, 'o');
		assertConverts("1", short.class, (short) 1);
		assertConverts("42", int.class, 42);
		assertConverts("42", long.class, 42L);
		assertConverts("42.23", float.class, 42.23f);
		assertConverts("42.23", double.class, 42.23);
	}
