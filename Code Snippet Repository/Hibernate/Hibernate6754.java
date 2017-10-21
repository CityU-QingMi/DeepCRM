	@Test
	public void testTypeDefinition() {
		PersistentClass pc = metadata().getEntityBinding( EntitySerialize.class.getName() );

		// explicitLob of SerializableToBlobType
		Type explicitLobType = pc.getProperty( "explicitLob" ).getType();
		assertEquals( ExplicitSerializable.class, explicitLobType.getReturnedClass() );
		assertEquals( SerializableToBlobType.class.getName(), explicitLobType.getName() );

		// explicit of ExplicitSerializableType
		Type explicitType = pc.getProperty( "explicit" ).getType();
		assertEquals( ExplicitSerializable.class, explicitType.getReturnedClass() );
		assertEquals( ExplicitSerializableType.class.getName(), explicitType.getName() );

		// implicit of ImplicitSerializableType
		Type implicitType = pc.getProperty( "implicit" ).getType();
		assertEquals( ImplicitSerializable.class, implicitType.getReturnedClass() );
		assertEquals( ImplicitSerializableType.class.getName(), implicitType.getName() );

		// explicitOverridingImplicit ExplicitSerializableType overrides ImplicitSerializableType
		Type overrideType = pc.getProperty( "explicitOverridingImplicit" ).getType();
		assertEquals( ImplicitSerializable.class, overrideType.getReturnedClass() );
		assertEquals( ExplicitSerializableType.class.getName(), overrideType.getName() );
	}
