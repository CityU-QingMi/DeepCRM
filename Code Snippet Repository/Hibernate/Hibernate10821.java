	@Test
	public void testHistoryOfCracow() {
		Address cracow = new Address( "Cracow", cracowId );
		Person kinga = new Person( "Kinga", kingaId );
		cracow.getTenants().add( kinga );
		cracow.setLandlord( kinga );

		Address ver2 = getAuditReader().find( Address.class, cracowId, 2 );
		Assert.assertEquals( cracow, ver2 );
		Assert.assertEquals( cracow.getTenants(), ver2.getTenants() );
		Assert.assertEquals( cracow.getLandlord().getId(), ver2.getLandlord().getId() );

		cracow.setCity( "Krakow" );

		Address ver3 = getAuditReader().find( Address.class, cracowId, 3 );
		Assert.assertEquals( cracow, ver3 );
	}
