	@Test
	@SuppressWarnings( {""})
	public void testInvalidHql() throws Exception {
		Exception newException = compileBadHql( "from Animal foo where an.bodyWeight > 10", false );
		assertTrue( "Wrong exception type!", newException instanceof QuerySyntaxException );
		newException = compileBadHql( "select an.name from Animal foo", false );
		assertTrue( "Wrong exception type!", newException instanceof QuerySyntaxException );
		newException = compileBadHql( "from Animal foo where an.verybogus > 10", false );
		assertTrue( "Wrong exception type!", newException instanceof QuerySyntaxException );
		newException = compileBadHql( "select an.boguspropertyname from Animal foo", false );
		assertTrue( "Wrong exception type!", newException instanceof QuerySyntaxException );
		newException = compileBadHql( "select an.name", false );
		assertTrue( "Wrong exception type!", newException instanceof QuerySyntaxException );
		newException = compileBadHql( "from Animal an where (((an.bodyWeight > 10 and an.bodyWeight < 100)) or an.bodyWeight is null", false );
		assertTrue( "Wrong exception type!", newException instanceof QuerySyntaxException );
		newException = compileBadHql( "from Animal an where an.bodyWeight is null where an.bodyWeight is null", false );
		assertTrue( "Wrong exception type!", newException instanceof QuerySyntaxException );
		newException = compileBadHql( "from where name='foo'", false );
		assertTrue( "Wrong exception type!", newException instanceof QuerySyntaxException );
		newException = compileBadHql( "from NonexistentClass where name='foo'", false );
		assertTrue( "Wrong exception type!", newException instanceof QuerySyntaxException );
		newException = compileBadHql( "select new FOO_BOGUS_Animal(an.description, an.bodyWeight) from Animal an", false );
		assertTrue( "Wrong exception type!", newException instanceof QuerySyntaxException );
		newException = compileBadHql( "select new Animal(an.description, an.bodyWeight, 666) from Animal an", false );
		assertTrue( "Wrong exception type!", newException instanceof QuerySyntaxException );

	}
