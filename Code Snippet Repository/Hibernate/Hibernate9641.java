	@Test
	public void testPlaceholderReplacement() {
		ConfigurationHelper.resolvePlaceHolders( props );

		String str = ConfigurationHelper.getString( "my.nonexistent.prop", props, "did.not.exist" );
		assertEquals( "did.not.exist", str );
		str = ConfigurationHelper.getString( "my.nonexistent.prop", props, null );
		assertNull( str );
		str = ConfigurationHelper.getString( "my.string.prop", props, "na" );
		assertEquals( "replacement did not occur", "string", str );
		str = ConfigurationHelper.getString( "my.string.prop", props, "did.not.exist" );
		assertEquals( "replacement did not occur", "string", str );

		boolean bool = ConfigurationHelper.getBoolean( "my.nonexistent.prop", props );
		assertFalse( "non-exists as boolean", bool );
		bool = ConfigurationHelper.getBoolean( "my.nonexistent.prop", props, false );
		assertFalse( "non-exists as boolean", bool );
		bool = ConfigurationHelper.getBoolean( "my.nonexistent.prop", props, true );
		assertTrue( "non-exists as boolean", bool );
		bool = ConfigurationHelper.getBoolean( "my.boolean.prop", props );
		assertTrue( "boolean replacement did not occur", bool );
		bool = ConfigurationHelper.getBoolean( "my.boolean.prop", props, false );
		assertTrue( "boolean replacement did not occur", bool );

		int i = ConfigurationHelper.getInt( "my.nonexistent.prop", props, -1 );
		assertEquals( -1, i );
		i = ConfigurationHelper.getInt( "my.int.prop", props, 100 );
		assertEquals( 1, i );

		Integer I = ConfigurationHelper.getInteger( "my.nonexistent.prop", props );
		assertNull( I );
		I = ConfigurationHelper.getInteger( "my.integer.prop", props );
		assertEquals( I, new Integer( 1 ) );

		str = props.getProperty( "partial.prop1" );
		assertEquals( "partial replacement (ends)", "tmp/middle/dir/tmp.txt", str );

		str = props.getProperty( "partial.prop2" );
		assertEquals( "partial replacement (midst)", "basedir/tmp/myfile.txt", str );
	}
