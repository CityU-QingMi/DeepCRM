	public static void doInHibernate(
			Supplier<SessionFactory> factorySupplier,
			String tenant,
			Consumer<Session> function) {
		Session session = null;
		Transaction txn = null;
		try {
			session = factorySupplier.get()
					.withOptions()
					.tenantIdentifier( tenant )
					.openSession();
			txn = session.getTransaction();
			txn.begin();
			function.accept( session );
			txn.commit();
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
