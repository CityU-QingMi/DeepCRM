	@Test
	public void testSerializeDeserialize() throws Exception {
		Class clazz = Thread.currentThread().getContextClassLoader().loadClass( "org.hibernate.test.util.SerializableThing" );
		Object instance = clazz.newInstance();

		// SerializableType.toBytes() logic, as called from SerializableType.disassemble()
		byte[] bytes = SerializationHelper.serialize( (Serializable) instance );

		// SerializableType.fromBytes() logic, as called from SerializableType.assemble
		//		NOTE : specifically we use Serializable.class.getClassLoader for the CL in many cases
		//			which are the problematic cases
		Object instance2 = SerializationHelper.deserialize( bytes, Serializable.class.getClassLoader() );

		assertEquals( instance.getClass(), instance2.getClass() );
		assertEquals( instance.getClass().getClassLoader(), instance2.getClass().getClassLoader() );
		assertEquals( custom, instance2.getClass().getClassLoader() );
	}
