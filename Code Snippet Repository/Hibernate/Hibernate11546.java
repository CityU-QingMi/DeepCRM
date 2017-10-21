	@SuppressWarnings( {""})
	public List<Integer> getContactsByTLF(String tlf) throws Exception {
		String selectHQL = "select contact.id from Contact contact"
			+ " where contact.tlf = :cTLF";

		return (List<Integer>) withTxSessionApply(s -> s.createQuery(selectHQL)
				.setFlushMode(FlushMode.AUTO)
				.setParameter("cTLF", tlf)
				.list());
	}
