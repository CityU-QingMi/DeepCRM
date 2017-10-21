	private Contact getFirstContact(Integer customerId) throws Exception {
		assert customerId != null;
		return withTxSessionApply(s -> {
			Customer customer = s.load(Customer.class, customerId);
			Set<Contact> contacts = customer.getContacts();
			Contact firstContact = contacts.isEmpty() ? null : contacts.iterator().next();
			if (TERMINATE_ALL_USERS) {
				markRollbackOnly(s);
			}
			return firstContact;
		});
	}
