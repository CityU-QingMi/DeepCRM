	@Test
	public void nonSerializableObject() {
		SerializingConverter toBytes = new SerializingConverter();
		try {
			toBytes.convert(new Object());
			fail("Expected IllegalArgumentException");
		}
		catch (SerializationFailedException e) {
			assertNotNull(e.getCause());
			assertTrue(e.getCause() instanceof IllegalArgumentException);
		}
	}
