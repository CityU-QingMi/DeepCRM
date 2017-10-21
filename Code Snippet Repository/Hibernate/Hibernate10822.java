	@Test
	public void testHistoryOfWarsaw() {
		Address warsaw = new Address( "Warsaw", warsawId );
		Person kinga = new Person( "Kinga", kingaId );
		Person lukasz = new Person( "Lukasz", lukaszId );
		warsaw.getTenants().add( lukasz );
		warsaw.getTenants().add( kinga );
		warsaw.setLandlord( lukasz );

		Address ver1 = getAuditReader().find( Address.class, warsawId, 1 );
		Assert.assertEquals( warsaw, ver1 );
		Assert.assertEquals( warsaw.getTenants(), ver1.getTenants() );
		Assert.assertEquals( warsaw.getLandlord().getId(), ver1.getLandlord().getId() );

		warsaw.setLandlord( kinga );

		Address ver5 = getAuditReader().find( Address.class, warsawId, 5 );
		Assert.assertEquals( warsaw.getLandlord().getId(), ver5.getLandlord().getId() );
	}
