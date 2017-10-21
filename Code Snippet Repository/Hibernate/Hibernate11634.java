	private Customer doGetCustomer(Integer id, Session session) throws Exception {
		Customer customer = session.get( Customer.class, id );
		if (customer == null) {
			return null;
		}
		// Access all the contacts
		Set<Contact> contacts = customer.getContacts();
		if (contacts != null) {
			for (Iterator it = contacts.iterator(); it.hasNext(); ) {
				((Contact) it.next()).getName();
			}
		}
		return customer;
	}
