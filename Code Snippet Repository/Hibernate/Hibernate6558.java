	@Test
	public void testTypeDefinition() {
		PersistentClass pc = metadata().getEntityBinding( EntityEnum.class.getName() );

		// ordinal default of EnumType
		Type ordinalEnum = pc.getProperty( "ordinal" ).getType();
		assertEquals( Common.class, ordinalEnum.getReturnedClass() );
		assertEquals( EnumType.class.getName(), ordinalEnum.getName() );

		// string defined by Enumerated(STRING)
		Type stringEnum = pc.getProperty( "string" ).getType();
		assertEquals( Common.class, stringEnum.getReturnedClass() );
		assertEquals( EnumType.class.getName(), stringEnum.getName() );

		// explicit defined by @Type
		Type first = pc.getProperty( "firstLetter" ).getType();
		assertEquals( FirstLetter.class, first.getReturnedClass() );
		assertEquals( FirstLetterType.class.getName(), first.getName() );

		// implicit defined by @TypeDef in somewhere
		Type last = pc.getProperty( "lastNumber" ).getType();
		assertEquals( LastNumber.class, last.getReturnedClass() );
		assertEquals( LastNumberType.class.getName(), last.getName() );

		// implicit defined by @TypeDef in anywhere, but overrided by Enumerated(STRING)
		Type implicitOverrideExplicit = pc.getProperty( "explicitOverridingImplicit" ).getType();
		assertEquals( LastNumber.class, implicitOverrideExplicit.getReturnedClass() );
		assertEquals( EnumType.class.getName(), implicitOverrideExplicit.getName() );
	}
