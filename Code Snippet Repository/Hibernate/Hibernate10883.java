	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getOrCreateEntityManager();
		try {
			// Revision 1
			em.getTransaction().begin();
			Customer customer = new Customer();
			customer.setCustomerNumber( "1234567" );
			customer.setName( "ACME" );
			em.persist( customer );
			em.getTransaction().commit();
			customerId = customer.getId();
			// Revision 2
			em.getTransaction().begin();
			Device device = new Device();
			device.setCustomer( customer );
			Account account = new Account();
			account.setCustomer( customer );
			em.persist( account );
			em.persist( device );
			em.getTransaction().commit();
			accountId = account.getId();
			deviceId = device.getId();
			// Revision 3
			em.getTransaction().begin();
			em.remove( account );
			em.getTransaction().commit();
		}
		finally {
			em.close();
		}
	}
