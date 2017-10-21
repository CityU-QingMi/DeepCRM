	private void removeContact(Integer customerId) throws Exception {
		assert customerId != null;

		withTxSession(s -> {
			Customer customer = s.load( Customer.class, customerId );
			Set<Contact> contacts = customer.getContacts();
			if ( contacts.size() != 1 ) {
				throw new IllegalStateException(
						"can't remove contact: customer id=" + customerId
								+ " expected exactly 1 contact, " + "actual count=" + contacts.size()
				);
			}

			Contact contact = contacts.iterator().next();
			// H2 version 1.3 (without MVCC fails with deadlock on Contacts/Customers modification, therefore,
			// we have to enforce locking Contacts first
			s.lock(contact, LockMode.PESSIMISTIC_WRITE);
			contacts.remove( contact );
			contact.setCustomer( null );

			// explicitly delete Contact because hbm has no 'DELETE_ORPHAN' cascade?
			// getEnvironment().getSessionFactory().getCurrentSession().delete(contact); //appears to
			// not be needed

			// assuming contact is persisted via cascade from customer

			if ( TERMINATE_ALL_USERS ) {
				markRollbackOnly(s);
			}
		});
	}
