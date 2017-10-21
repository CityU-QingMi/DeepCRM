	@Test
	public void testNotOrWhereClause() {
		assertTranslation( "from Simple s where 'foo'='bar' or not 'foo'='foo'" );
		assertTranslation( "from Simple s where 'foo'='bar' or not ('foo'='foo')" );
		assertTranslation( "from Simple s where not ( 'foo'='bar' or 'foo'='foo' )" );
		assertTranslation( "from Simple s where not ( 'foo'='bar' and 'foo'='foo' )" );
		assertTranslation( "from Simple s where not ( 'foo'='bar' and 'foo'='foo' ) or not ('x'='y')" );
		assertTranslation( "from Simple s where not ( 'foo'='bar' or 'foo'='foo' ) and not ('x'='y')" );
		assertTranslation( "from Simple s where not ( 'foo'='bar' or 'foo'='foo' ) and 'x'='y'" );
		assertTranslation( "from Simple s where not ( 'foo'='bar' and 'foo'='foo' ) or 'x'='y'" );
		assertTranslation( "from Simple s where 'foo'='bar' and 'foo'='foo' or not 'x'='y'" );
		assertTranslation( "from Simple s where 'foo'='bar' or 'foo'='foo' and not 'x'='y'" );
		assertTranslation( "from Simple s where ('foo'='bar' and 'foo'='foo') or 'x'='y'" );
		assertTranslation( "from Simple s where ('foo'='bar' or 'foo'='foo') and 'x'='y'" );
		assertTranslation( "from Simple s where not( upper( s.name ) ='yada' or 1=2 or 'foo'='bar' or not('foo'='foo') or 'foo' like 'bar' )" );
	}
