	private void cleanup(SessionFactory sessionFactory) throws Exception {
		withTxSession(sessionFactory, session -> {
			Customer c = (Customer) session.get(Customer.class, CUSTOMER_ID);
			if (c != null) {
				Set contacts = c.getContacts();
				for (Iterator it = contacts.iterator(); it.hasNext(); ) {
					session.delete(it.next());
				}
				c.setContacts(null);
				session.delete(c);
			}
			// since we don't use orphan removal, some contacts may persist
			for (Object contact : session.createCriteria(Contact.class).list()) {
				session.delete(contact);
			}
		});
	}
