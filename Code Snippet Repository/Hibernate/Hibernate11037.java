	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		Person person = new Person( "Robert" );
		Account account = new Account( "Saving" );
		person.setAccount( account );
		account.setOwner( person );
		em.persist( person );
		em.persist( account );
		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();
		NotAuditedNoProxyPerson noProxyPerson = new NotAuditedNoProxyPerson( "Kinga" );
		NotAuditedProxyPerson proxyPerson = new NotAuditedProxyPerson( "Lukasz" );
		AccountNotAuditedOwners accountNotAuditedOwners = new AccountNotAuditedOwners( "Standard" );
		noProxyPerson.setAccount( accountNotAuditedOwners );
		proxyPerson.setAccount( accountNotAuditedOwners );
		accountNotAuditedOwners.setOwner( noProxyPerson );
		accountNotAuditedOwners.setCoOwner( proxyPerson );
		em.persist( accountNotAuditedOwners );
		em.persist( noProxyPerson );
		em.persist( proxyPerson );
		em.getTransaction().commit();

		personId = person.getPersonId();
		accountId = account.getAccountId();
		accountNotAuditedOwnersId = accountNotAuditedOwners.getAccountId();
		proxyPersonId = proxyPerson.getPersonId();
		noProxyPersonId = noProxyPerson.getPersonId();
	}
