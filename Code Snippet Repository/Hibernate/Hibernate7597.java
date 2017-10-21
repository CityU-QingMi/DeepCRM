	private void performBasicSpaceTrimmingTests(AnsiTrimEmulationFunction function) {
		// -> trim(a.column)
		String rendered = function.render( null, argList( trimSource ), null );
		assertEquals( "ltrim(rtrim(a.column))", rendered );

		// -> trim(FROM a.column)
		rendered = function.render( null, argList( "FROM", trimSource ), null );
		assertEquals( "ltrim(rtrim(a.column))", rendered );

		// -> trim(BOTH FROM a.column)
		rendered = function.render( null, argList( "BOTH", "FROM", trimSource ), null );
		assertEquals( "ltrim(rtrim(a.column))", rendered );

		// -> trim(BOTH ' ' FROM a.column)
		rendered = function.render( null, argList( "BOTH", "' '", "FROM", trimSource ), null );
		assertEquals( "ltrim(rtrim(a.column))", rendered );

		// -> trim(LEADING FROM a.column)
		rendered = function.render( null, argList( "LEADING", "FROM", trimSource ), null );
		assertEquals( "ltrim(a.column)", rendered );

		// -> trim(LEADING ' ' FROM a.column)
		rendered = function.render( null, argList( "LEADING", "' '", "FROM", trimSource ), null );
		assertEquals( "ltrim(a.column)", rendered );

		// -> trim(TRAILING FROM a.column)
		rendered = function.render( null, argList( "TRAILING", "FROM", trimSource ), null );
		assertEquals( "rtrim(a.column)", rendered );

		// -> trim(TRAILING ' ' FROM a.column)
		rendered = function.render( null, argList( "TRAILING", "' '", "FROM", trimSource ), null );
		assertEquals( "rtrim(a.column)", rendered );
	}
