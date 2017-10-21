	@SuppressWarnings( {""})
	public List<Integer> getContactsByCustomer(String customerName) throws Exception {
		String selectHQL = "select contact.id from Contact contact"
			+ " where contact.customer.name = :cName";

		return (List<Integer>) withTxSessionApply(s -> s.createQuery(selectHQL)
				.setFlushMode(FlushMode.AUTO)
				.setParameter("cName", customerName)
				.list());
	}
