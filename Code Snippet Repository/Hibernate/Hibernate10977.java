	@Test
	public void testHistoryOfPersonC() {
		Person person = getAuditReader().find( Person.class, personCVer1.getId(), 2 );

		Assert.assertEquals( personCVer1, person );
		Assert.assertEquals( personCVer1.getPersonATuples(), person.getPersonATuples() );
		Assert.assertEquals( personCVer1.getPersonBTuples(), person.getPersonBTuples() );

		person = getAuditReader().find( Person.class, personCVer2.getId(), 4 );

		Assert.assertEquals( personCVer2, person );
	}
