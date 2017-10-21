	@Test
	public void testRevisionHistoryOfCustomer() {
		final Customer customer = new Customer( customerId, "1234567", "ACME" );
		Customer rev1 = getAuditReader().find( Customer.class, customerId, 1 );
		assertEquals( customer, rev1 );

		final Account account = new Account( accountId, customer );
		final Device device = new Device( deviceId, customer );
		customer.getAccounts().add( account );
		customer.getDevices().add( device );
		Customer rev2 = getAuditReader().find( Customer.class, customerId, 2 );
		assertEquals( customer, rev2 );

		customer.getAccounts().clear();
		Customer rev3 = getAuditReader().find( Customer.class, customerId, 3 );
		assertEquals( customer, rev3 );
	}
