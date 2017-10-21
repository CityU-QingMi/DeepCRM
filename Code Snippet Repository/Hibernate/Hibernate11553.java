	private Contact addContact(Integer customerId) throws Exception {
		assert customerId != null;
		return withTxSessionApply(s -> {
			final Customer customer = s.load(Customer.class, customerId);
			Contact contact = new Contact();
			contact.setName("contact name");
			contact.setTlf("wtf is tlf?");
			contact.setCustomer(customer);
			customer.getContacts().add(contact);
			// assuming contact is persisted via cascade from customer
			if (TERMINATE_ALL_USERS) {
				markRollbackOnly(s);
			}
			return contact;
		});
	}
