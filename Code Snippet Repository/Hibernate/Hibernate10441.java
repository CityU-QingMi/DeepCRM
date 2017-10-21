	@Test
	public void testHistoryOfCharacter() {
		DarkCharacter darkCharacter = new DarkCharacter( id, 1 );

		DarkCharacter ver1 = getAuditReader().find( DarkCharacter.class, id, 1 );

		Assert.assertEquals( darkCharacter, ver1 );
		Assert.assertEquals( 0, ver1.getNames().size() );

		darkCharacter.getNames().add( new Name( "Action", "Hank" ) );
		DarkCharacter ver2 = getAuditReader().find( DarkCharacter.class, id, 2 );

		Assert.assertEquals( darkCharacter, ver2 );
		Assert.assertEquals( darkCharacter.getNames(), ver2.getNames() );

		darkCharacter.getNames().add( new Name( "Green", "Lantern" ) );
		DarkCharacter ver3 = getAuditReader().find( DarkCharacter.class, id, 3 );

		Assert.assertEquals( darkCharacter, ver3 );
		Assert.assertEquals( darkCharacter.getNames(), ver3.getNames() );

		darkCharacter.getNames().remove( new Name( "Action", "Hank" ) );
		DarkCharacter ver4 = getAuditReader().find( DarkCharacter.class, id, 4 );

		Assert.assertEquals( darkCharacter, ver4 );
		Assert.assertEquals( darkCharacter.getNames(), ver4.getNames() );

		darkCharacter.getNames().clear();
		DarkCharacter ver5 = getAuditReader().find( DarkCharacter.class, id, 5 );

		Assert.assertEquals( darkCharacter, ver5 );
		Assert.assertEquals( darkCharacter.getNames(), ver5.getNames() );
	}
