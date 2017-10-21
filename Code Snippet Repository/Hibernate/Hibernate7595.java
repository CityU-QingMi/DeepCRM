    @Test
	public void testBasicSqlServerProcessing() {
		AnsiTrimEmulationFunction function = new AnsiTrimEmulationFunction();

		performBasicSpaceTrimmingTests( function );

		final String expectedTrimPrep = "replace(replace(a.column,' ','${space}$'),'-',' ')";
		final String expectedPostTrimPrefix = "replace(replace(";
		final String expectedPostTrimSuffix = ",' ','-'),'${space}$',' ')";

		// -> trim(LEADING '-' FROM a.column)
		String rendered = function.render( null, argList( "LEADING", "'-'", "FROM", trimSource ), null );
		String expected = expectedPostTrimPrefix + "ltrim(" + expectedTrimPrep + ")" + expectedPostTrimSuffix;
		assertEquals( expected, rendered );

		// -> trim(TRAILING '-' FROM a.column)
		rendered = function.render( null, argList( "TRAILING", "'-'", "FROM", trimSource ), null );
		expected = expectedPostTrimPrefix + "rtrim(" + expectedTrimPrep + ")" + expectedPostTrimSuffix;
		assertEquals( expected, rendered );

		// -> trim(BOTH '-' FROM a.column)
		rendered = function.render( null, argList( "BOTH", "'-'", "FROM", trimSource ), null );
		expected = expectedPostTrimPrefix + "ltrim(rtrim(" + expectedTrimPrep + "))" + expectedPostTrimSuffix;
		assertEquals( expected, rendered );

		// -> trim('-' FROM a.column)
		rendered = function.render( null, argList( "'-'", "FROM", trimSource ), null );
		expected = expectedPostTrimPrefix + "ltrim(rtrim(" + expectedTrimPrep + "))" + expectedPostTrimSuffix;
		assertEquals( expected, rendered );
	}
