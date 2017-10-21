	@Test
	public void testNativeSQLQueryScalarReturn() {
		NativeSQLQueryScalarReturn typeNoAlias = new NativeSQLQueryScalarReturn( null,sessionFactory().getTypeResolver().basic( "int" ) );
		NativeSQLQueryScalarReturn aliasNoType = new NativeSQLQueryScalarReturn( "abc", null );
		NativeSQLQueryScalarReturn aliasTypeInt = new NativeSQLQueryScalarReturn( "abc",sessionFactory().getTypeResolver().basic( "int" ) );
		NativeSQLQueryScalarReturn aliasTypeLong =  new NativeSQLQueryScalarReturn( "abc",sessionFactory().getTypeResolver().basic( "long" ) );
		NativeSQLQueryScalarReturn aliasTypeLongClass =  new NativeSQLQueryScalarReturn( "abc",sessionFactory().getTypeResolver().basic( Long.class.getName() ) );
		NativeSQLQueryScalarReturn aliasTypeString =  new NativeSQLQueryScalarReturn( "abc",sessionFactory().getTypeResolver().basic( "string" ) );
		NativeSQLQueryScalarReturn aliasTypeStringClass =  new NativeSQLQueryScalarReturn( "abc",sessionFactory().getTypeResolver().basic( String.class.getName() ) );

		check( false, typeNoAlias, aliasNoType );
		check( false, typeNoAlias, aliasTypeInt );
		check( false, typeNoAlias, aliasTypeLong );
		check( false, typeNoAlias, aliasTypeLongClass );
		check( false, typeNoAlias, aliasTypeString );
		check( false, typeNoAlias, aliasTypeStringClass );

		check( false, aliasNoType, aliasTypeInt );
		check( false, aliasNoType, aliasTypeLong );
		check( false, aliasNoType, aliasTypeLongClass );
		check( false, aliasNoType, aliasTypeString );
		check( false, aliasNoType, aliasTypeStringClass );

		check( false, aliasTypeInt, aliasTypeLong );
		check( false, aliasTypeInt, aliasTypeLongClass );
		check( false, aliasTypeInt, aliasTypeString );
		check( false, aliasTypeInt, aliasTypeStringClass );

		check( true, aliasTypeLong, aliasTypeLongClass );
		check( false, aliasTypeLong, aliasTypeString );
		check( false, aliasTypeLong, aliasTypeStringClass );

		check( false, aliasTypeLongClass, aliasTypeString );
		check( false, aliasTypeLongClass, aliasTypeStringClass );

		check( true, aliasTypeString, aliasTypeStringClass );

		check( true, typeNoAlias, new NativeSQLQueryScalarReturn( null,sessionFactory().getTypeResolver().basic( "int" ) ) );
		check( true, aliasNoType, new NativeSQLQueryScalarReturn( "abc", null ) );
		check( true, aliasTypeInt, new NativeSQLQueryScalarReturn( "abc",sessionFactory().getTypeResolver().basic( "int" ) ) );
		check( true, aliasTypeLong, new NativeSQLQueryScalarReturn( "abc",sessionFactory().getTypeResolver().basic( "long" ) ) );
		check( true, aliasTypeLongClass,  new NativeSQLQueryScalarReturn( "abc",sessionFactory().getTypeResolver().basic( Long.class.getName() ) ) );
		check( true, aliasTypeString, new NativeSQLQueryScalarReturn( "abc",sessionFactory().getTypeResolver().basic( "string" ) ) );
		check( true, aliasTypeStringClass, new NativeSQLQueryScalarReturn( "abc",sessionFactory().getTypeResolver().basic( String.class.getName() ) ) );
	}
