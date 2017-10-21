		private void release() {
			Session session = openSession();
			Transaction transaction = session.beginTransaction();

			Iterator itr = entitiesToCleanUp.iterator();
			while ( itr.hasNext() ) {
				session.delete( itr.next() );
			}

			transaction.commit();
			session.close();
		}
