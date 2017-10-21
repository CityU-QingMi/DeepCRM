	public final Serializable doInsideTransaction(Session s, java.util.function.Supplier<Serializable> sp) {
		s.getTransaction().begin();
		try {
			final Serializable result = sp.get();

			s.getTransaction().commit();
			return result;
		}
		catch (Exception e) {
			if ( s.getTransaction().getStatus() == TransactionStatus.ACTIVE ) {
				s.getTransaction().rollback();
			}
			throw e;
		}
	}
