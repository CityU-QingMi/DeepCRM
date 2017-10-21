	private ChildEntity2 createChildEntity2() {
		ChildEntity2 ce = new ChildEntity2();
		ce.setId( idCounter++ );
		ce.setNumber( 12345678 );
		ce.setData( "some data, not really meaningful" );
		ce.setStrings( new HashSet<String>() );
		ce.getStrings().add( "aaa" );
		ce.getStrings().add( "bbb" );
		ce.getStrings().add( "ccc" );

		return ce;
	}
