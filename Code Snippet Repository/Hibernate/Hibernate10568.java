	@Test
	@TestForIssue(jiraKey = "")
	public void testSingleDomainObjectToMultipleTablesMapping() {
		Car carVer1 = getAuditReader().find( Car.class, carId, 1 );
		Person ownerVer1 = getAuditReader().find( Person.class, "Personaje", ownerId, 1 );
		Person driverVer1 = getAuditReader().find( Person.class, "Driveraje", driverId, 1 );

        /* Check ids. */
		Assert.assertEquals( ownerVer1.getId(), carVer1.getOwner().getId() );
		Assert.assertEquals( driverVer1.getId(), carVer1.getDriver().getId() );

        /* Check object properties. */
		Assert.assertEquals( "Lukasz", ownerVer1.getName() );
		Assert.assertEquals( "Kinga", driverVer1.getName() );
		Assert.assertEquals( 1, carVer1.getNumber() );
	}
