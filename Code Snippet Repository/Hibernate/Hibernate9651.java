	public void testSerDeserClassUnknownToCustomLoader() throws Exception {
		Object instance = LockMode.OPTIMISTIC;
		assertSame( 
			SerializationHelper.hibernateClassLoader(),
			instance.getClass().getClassLoader() 
		);

		// SerializableType.toBytes() logic, as called from SerializableType.disassemble()
		byte[] bytes = SerializationHelper.serialize( (Serializable) instance );

		// SerializableType.fromBytes() logic, as called from SerializableType.assemble
		// NOTE : specifically we use custom so that LockType.class is not found
		//        until the 3rd loader (because loader1 == loader2, the custom classloader)
		Object instance2 = SerializationHelper.deserialize( bytes, custom );

		assertSame( instance.getClass(), instance2.getClass() );
		assertSame( instance.getClass().getClassLoader(), instance2.getClass().getClassLoader() );
	}
