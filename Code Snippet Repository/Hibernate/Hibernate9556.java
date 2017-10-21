	@Test
	public void testOverriding() {
		BasicTypeRegistry registry = new BasicTypeRegistry();

		BasicType type = registry.getRegisteredType( "uuid-binary" );
		assertSame( UUIDBinaryType.INSTANCE, type );
		type = registry.getRegisteredType( UUID.class.getName() );
		assertSame( UUIDBinaryType.INSTANCE, type );

		BasicType override = new UUIDCharType() {
			@Override
			protected boolean registerUnderJavaType() {
				return true;
			}
		};
		registry.register( override );
		type = registry.getRegisteredType( UUID.class.getName() );
		assertNotSame( UUIDBinaryType.INSTANCE, type );
		assertSame( override, type );
	}
