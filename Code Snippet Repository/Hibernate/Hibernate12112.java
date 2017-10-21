	public static <R> R doInHibernate(
			Supplier<SessionFactory> factorySupplier,
			String tenant,
			Function<Session, R> function) {
		Session session = null;
		Transaction txn = null;
		try {
			session = factorySupplier.get()
					.withOptions()
					.tenantIdentifier( tenant )
					.openSession();
			txn = session.getTransaction();
			txn.begin();
			R returnValue = function.apply( session );
			txn.commit();
			return returnValue;
		}
		catch (Throwable e) {
			if ( txn != null ) {
				txn.rollback();
			}
			throw e;
		}
		finally {
			if ( session != null ) {
				session.close();
			}
		}
	}
