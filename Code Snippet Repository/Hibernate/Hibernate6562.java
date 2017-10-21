	@Test
	public void testTypeDefinition() {
		PersistentClass pc = metadata().getEntityBinding( EntityMapEnum.class.getName() );

		// ordinal default of EnumType
		assetTypeDefinition( pc.getProperty( "ordinalMap" ), Common.class, EnumType.class );

		// string defined by Enumerated(STRING)
		assetTypeDefinition( pc.getProperty( "stringMap" ), Common.class, EnumType.class );

		// explicit defined by @Type
		assetTypeDefinition( pc.getProperty( "firstLetterMap" ), FirstLetter.class, FirstLetterType.class );

		// implicit defined by @TypeDef in somewhere
		assetTypeDefinition( pc.getProperty( "lastNumberMap" ), LastNumber.class, LastNumberType.class );

		// implicit defined by @TypeDef in anywhere, but overrided by Enumerated(STRING)
		assetTypeDefinition( pc.getProperty( "explicitOverridingImplicitMap" ), LastNumber.class, EnumType.class );
	}
