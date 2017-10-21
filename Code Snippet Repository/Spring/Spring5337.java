	@Test
	public void nonSerializableField() {
		SerializingConverter toBytes = new SerializingConverter();
		try {
			toBytes.convert(new UnSerializable());
			fail("Expected SerializationFailureException");
		}
		catch (SerializationFailedException e) {
			assertNotNull(e.getCause());
			assertTrue(e.getCause() instanceof NotSerializableException);
		}
	}
