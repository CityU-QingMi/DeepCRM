	@Test
	public void testRegisteringUserTypes() {
		BasicTypeRegistry registry = new BasicTypeRegistry();

		registry.register( new TotallyIrrelevantUserType(), new String[] { "key" } );
		BasicType type = registry.getRegisteredType( "key" );
		assertNotNull( type );
		assertEquals( CustomType.class, type.getClass() );
		assertEquals( TotallyIrrelevantUserType.class, ( (CustomType) type ).getUserType().getClass() );

		registry.register( new TotallyIrrelevantCompositeUserType(), new String[] { "key" } );
		type = registry.getRegisteredType( "key" );
		assertNotNull( type );
		assertEquals( CompositeCustomType.class, type.getClass() );
		assertEquals( TotallyIrrelevantCompositeUserType.class, ( (CompositeCustomType) type ).getUserType().getClass() );

		type = registry.getRegisteredType( UUID.class.getName() );
		assertSame( UUIDBinaryType.INSTANCE, type );
		registry.register( new TotallyIrrelevantUserType(), new String[] { UUID.class.getName() } );
		type = registry.getRegisteredType( UUID.class.getName() );
		assertNotSame( UUIDBinaryType.INSTANCE, type );
		assertEquals( CustomType.class, type.getClass() );
	}
