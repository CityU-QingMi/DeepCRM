	public int updateContactsWithOneManual(String name, String newTLF) throws Exception {
		String queryHQL = "from Contact c where c.name = :cName";
		String updateHQL = "update Contact set tlf = :cNewTLF where name = :cName";
		return withTxSessionApply(s -> {
			List<Contact> list = s.createQuery(queryHQL).setParameter("cName", name).list();
			list.get(0).setTlf(newTLF);
			return s.createQuery(updateHQL)
					.setFlushMode(FlushMode.AUTO)
					.setParameter("cNewTLF", newTLF)
					.setParameter("cName", name)
					.executeUpdate();
		});
	}
