	@Test
	public void testHistoryOfAccountNotAuditedOwners() {
		NotAuditedNoProxyPerson noProxyPersonVer1 = new NotAuditedNoProxyPerson( noProxyPersonId, "Kinga" );
		NotAuditedProxyPerson proxyPersonVer1 = new NotAuditedProxyPerson( proxyPersonId, "Lukasz" );
		AccountNotAuditedOwners accountNotAuditedOwnersVer1 = new AccountNotAuditedOwners(
				accountNotAuditedOwnersId,
				"Standard"
		);
		noProxyPersonVer1.setAccount( accountNotAuditedOwnersVer1 );
		proxyPersonVer1.setAccount( accountNotAuditedOwnersVer1 );
		accountNotAuditedOwnersVer1.setOwner( noProxyPersonVer1 );
		accountNotAuditedOwnersVer1.setCoOwner( proxyPersonVer1 );

		Object[] result = ((Object[]) getAuditReader().createQuery()
				.forRevisionsOfEntity( AccountNotAuditedOwners.class, false, true )
				.add( AuditEntity.id().eq( accountNotAuditedOwnersId ) )
				.getResultList()
				.get( 0 ));

		Assert.assertEquals( accountNotAuditedOwnersVer1, result[0] );
		Assert.assertEquals( RevisionType.ADD, result[2] );
		// Checking non-proxy reference
		Assert.assertEquals( accountNotAuditedOwnersVer1.getOwner(), ((AccountNotAuditedOwners) result[0]).getOwner() );
		// Checking proxy reference
		Assert.assertTrue( ((AccountNotAuditedOwners) result[0]).getCoOwner() instanceof HibernateProxy );
		Assert.assertEquals(
				proxyPersonVer1.getPersonId(),
				((AccountNotAuditedOwners) result[0]).getCoOwner().getPersonId()
		);

		Assert.assertEquals(
				accountNotAuditedOwnersVer1, getAuditReader().find(
				AccountNotAuditedOwners.class,
				accountNotAuditedOwnersId,
				2
		)
		);
	}
