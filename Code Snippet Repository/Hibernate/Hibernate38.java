	private void withStatelessSession() {
		withBatch();

		//tag::batch-stateless-session-example[]
		StatelessSession statelessSession = null;
		Transaction txn = null;
		ScrollableResults scrollableResults = null;
		try {
			SessionFactory sessionFactory = entityManagerFactory().unwrap( SessionFactory.class );
			statelessSession = sessionFactory.openStatelessSession();

			txn = statelessSession.getTransaction();
			txn.begin();

			scrollableResults = statelessSession
				.createQuery( "select p from Person p" )
				.scroll(ScrollMode.FORWARD_ONLY);

			while ( scrollableResults.next() ) {
				Person Person = (Person) scrollableResults.get( 0 );
				processPerson(Person);
				statelessSession.update( Person );
			}

			txn.commit();
		} catch (RuntimeException e) {
			if ( txn != null && txn.getStatus() == TransactionStatus.ACTIVE) txn.rollback();
				throw e;
		} finally {
			if (scrollableResults != null) {
				scrollableResults.close();
			}
			if (statelessSession != null) {
				statelessSession.close();
			}
		}
		//end::batch-stateless-session-example[]
	}
