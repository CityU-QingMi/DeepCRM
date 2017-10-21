	@Test
	public void testHistoryOfOwned() {
		OneToManyOwned owned = new OneToManyOwned( "data", null, ownedId );
		ManyToOneOwning owning1 = new ManyToOneOwning( "data1", owned, owning1Id );
		ManyToOneOwning owning2 = new ManyToOneOwning( "data2", owned, owning2Id );

		OneToManyOwned ver1 = getAuditReader().find( OneToManyOwned.class, ownedId, 1 );
		Assert.assertEquals( owned, ver1 );
		Assert.assertEquals( TestTools.makeSet( owning1, owning2 ), ver1.getReferencing() );

		OneToManyOwned ver2 = getAuditReader().find( OneToManyOwned.class, ownedId, 2 );
		Assert.assertEquals( owned, ver2 );
		Assert.assertEquals( TestTools.makeSet( owning2 ), ver2.getReferencing() );
	}
