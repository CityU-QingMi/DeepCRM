	@Test
	public void testHistoryOfAccount() {
		Person personVer1 = new Person( personId, "Robert" );
		Account accountVer1 = new Account( accountId, "Saving" );
		personVer1.setAccount( accountVer1 );
		accountVer1.setOwner( personVer1 );

		Object[] result = ((Object[]) getAuditReader().createQuery().forRevisionsOfEntity( Account.class, false, true )
				.add( AuditEntity.id().eq( accountId ) )
				.getResultList().get( 0 ));

		Assert.assertEquals( accountVer1, result[0] );
		Assert.assertEquals( accountVer1.getOwner(), ((Account) result[0]).getOwner() );
		Assert.assertEquals( RevisionType.ADD, result[2] );

		Assert.assertEquals( accountVer1, getAuditReader().find( Account.class, accountId, 1 ) );
	}
