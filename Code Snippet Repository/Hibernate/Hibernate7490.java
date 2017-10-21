	@Test
	public void testIntegerWrapperThrowsException() {
		EntityConverter entity = new EntityConverter();
		entity.setIntegerWrapper( new IntegerWrapper( 10 ) );
		save( entity );

		try {
			find( entity.getId(), "e.integerWrapper='10'" );
			fail("Should throw QueryException!");
		}
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
			assertTrue( e.getMessage().contains( "AttributeConverter domain-model attribute type [org.hibernate.test.converter.literal.QueryLiteralTest$IntegerWrapper] and JDBC type [java.lang.Integer] did not match query literal type [java.lang.String]" ) );
		}
	}
