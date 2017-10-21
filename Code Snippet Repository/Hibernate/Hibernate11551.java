	private void readEveryonesFirstContact() throws Exception {
		withTxSession(s -> {
			for ( Integer customerId : getCustomerIDs() ) {
				if ( TERMINATE_ALL_USERS ) {
					markRollbackOnly(s);
					return;
				}
				Customer customer = s.load( Customer.class, customerId );
				Set<Contact> contacts = customer.getContacts();
				if ( !contacts.isEmpty() ) {
					contacts.iterator().next();
				}
			}
		});
	}
