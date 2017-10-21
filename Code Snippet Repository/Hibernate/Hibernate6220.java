	@Test
	public void testGet() throws Exception {
		Target target = new Target();

		assertEquals( true, getter( "active" ).get( target ) );
		assertEquals( (byte) 2, getter( "children" ).get( target ) );
		assertEquals( 'M', getter( "gender" ).get( target ) );
		assertEquals( Integer.MAX_VALUE, getter( "code" ).get( target ) );
		assertEquals( Long.MAX_VALUE, getter( "id" ).get( target ) );
		assertEquals( (short) 34, getter( "age" ).get( target ) );
		assertEquals( "John Doe", getter( "name" ).get( target ) );
	}
