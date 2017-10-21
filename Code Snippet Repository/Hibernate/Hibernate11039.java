	@Test
	public void testHistoryOfPerson() {
		Person personVer1 = new Person( personId, "Robert" );
		Account accountVer1 = new Account( accountId, "Saving" );
		personVer1.setAccount( accountVer1 );
		accountVer1.setOwner( personVer1 );

		Object[] result = ((Object[]) getAuditReader().createQuery().forRevisionsOfEntity( Person.class, false, true )
				.add( AuditEntity.id().eq( personId ) )
				.getResultList().get( 0 ));

		Assert.assertEquals( personVer1, result[0] );
		Assert.assertEquals( personVer1.getAccount(), ((Person) result[0]).getAccount() );
		Assert.assertEquals( RevisionType.ADD, result[2] );

		Assert.assertEquals( personVer1, getAuditReader().find( Person.class, personId, 1 ) );
	}
