	@Test
	@Priority(10)
	public void initData() {
		id1 = addNewEntity( "x", 1 ); // rev 1
		id2 = addNewEntity( "y", 20 ); // rev 2
		id3 = addNewEntity( "z", 30 ); // rev 3

		modifyEntity( id1, "x2", 2 ); // rev 4
		modifyEntity( id2, "y2", 20 ); // rev 5
		modifyEntity( id1, "x3", 3 ); // rev 6
		modifyEntity( id1, "x3", 3 ); // no rev
		modifyEntity( id2, "y3", 21 ); // rev 7
	}
