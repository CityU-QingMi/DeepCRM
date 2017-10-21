	@Test
	public void testHistoryOfOwning2() {
		OneToManyOwned owned = new OneToManyOwned( "data", null, ownedId );
		ManyToOneOwning owning1 = new ManyToOneOwning( "data2", owned, owning2Id );
		ManyToOneOwning owning3 = new ManyToOneOwning( "data2modified", owned, owning2Id );

		ManyToOneOwning ver1 = getAuditReader().find( ManyToOneOwning.class, owning2Id, 1 );
		ManyToOneOwning ver3 = getAuditReader().find( ManyToOneOwning.class, owning2Id, 3 );

		Assert.assertEquals( owning1, ver1 );
		Assert.assertEquals( owned.getId(), ver1.getReferences().getId() );
		Assert.assertEquals( owning3, ver3 );
		Assert.assertEquals( owned.getId(), ver3.getReferences().getId() );
	}
