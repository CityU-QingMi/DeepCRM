	@Test
	public void testExpanding() {
		BasicTypeRegistry registry = new BasicTypeRegistry();

		BasicType type = registry.getRegisteredType( SomeNoopType.INSTANCE.getName() );
		assertNull( type );

		registry.register( SomeNoopType.INSTANCE );
		type = registry.getRegisteredType( SomeNoopType.INSTANCE.getName() );
		assertNotNull( type );
		assertSame( SomeNoopType.INSTANCE, type );
	}
