	@Test
	public void testHistoryOfKinga() {
		Person kinga = new Person( "Kinga", kingaId );
		Address warsaw = new Address( "Warsaw", warsawId );
		kinga.getAddresses().add( warsaw );

		Person ver1 = getAuditReader().find( Person.class, kingaId, 1 );
		Assert.assertEquals( kinga, ver1 );
		Assert.assertEquals( kinga.getAddresses(), ver1.getAddresses() );
		Assert.assertEquals( kinga.getOwnedAddresses(), ver1.getOwnedAddresses() );

		Address cracow = new Address( "Cracow", cracowId );
		kinga.getOwnedAddresses().add( cracow );
		kinga.getAddresses().add( cracow );

		Person ver2 = getAuditReader().find( Person.class, kingaId, 2 );
		Assert.assertEquals( kinga, ver2 );
		Assert.assertEquals( kinga.getAddresses(), ver2.getAddresses() );
		Assert.assertEquals( kinga.getOwnedAddresses(), ver2.getOwnedAddresses() );

		kinga.getOwnedAddresses().add( warsaw );
		cracow.setCity( "Krakow" );

		Person ver5 = getAuditReader().find( Person.class, kingaId, 5 );
		Assert.assertEquals( TestTools.makeSet( kinga.getAddresses() ), TestTools.makeSet( ver5.getAddresses() ) );
		Assert.assertEquals(
				TestTools.makeSet( kinga.getOwnedAddresses() ),
				TestTools.makeSet( ver5.getOwnedAddresses() )
		);
	}
