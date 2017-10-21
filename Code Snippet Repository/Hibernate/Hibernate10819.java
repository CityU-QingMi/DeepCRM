	@Test
	public void testHistoryOfLukasz() {
		Person lukasz = new Person( "Lukasz", lukaszId );
		Address warsaw = new Address( "Warsaw", warsawId );
		lukasz.getAddresses().add( warsaw );
		lukasz.getOwnedAddresses().add( warsaw );

		Person ver1 = getAuditReader().find( Person.class, lukaszId, 1 );
		Assert.assertEquals( lukasz, ver1 );
		Assert.assertEquals( lukasz.getAddresses(), ver1.getAddresses() );
		Assert.assertEquals( lukasz.getOwnedAddresses(), ver1.getOwnedAddresses() );

		lukasz.setName( "Lucas" );

		Person ver4 = getAuditReader().find( Person.class, lukaszId, 4 );
		Assert.assertEquals( lukasz, ver4 );

		lukasz.getOwnedAddresses().remove( warsaw );

		Person ver5 = getAuditReader().find( Person.class, lukaszId, 5 );
		Assert.assertEquals( lukasz.getOwnedAddresses(), ver5.getOwnedAddresses() );
	}
