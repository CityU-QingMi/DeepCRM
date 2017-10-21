	private ChildEntity1 createChildEntity1() {
		ChildEntity1 ce = new ChildEntity1();
		ce.setId( idCounter++ );
		ce.setData1( "xxx" );
		ce.setData2( "yyy" );
		ce.setChild1( createChildEntity2() );
		ce.setChild2( createChildEntity2() );

		return ce;
	}
