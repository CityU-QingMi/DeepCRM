	@Test
	public void testUpdateEntityWithPackageNameStartingWithFrom() {
		In entity = new In();
		entity.setProp( "1" );
		try (Session session = openSession()) {

			session.getTransaction().begin();
			try {
				session.save( entity );
				session.getTransaction().commit();
			}
			catch (Exception e) {
				if ( session.getTransaction().getStatus() == TransactionStatus.ACTIVE ) {
					session.getTransaction().rollback();
				}
				throw e;
			}
		}
		try (Session session = openSession()) {
			session.getTransaction().begin();
			try {
				final Query query = session.createQuery( "UPDATE In set prop = :prop WHERE id = :id " );
				query.setParameter( "prop", "1" );
				query.setParameter( "id", entity.getId() );
				query.executeUpdate();
				session.getTransaction().commit();
			}
			catch (Exception e) {
				if ( session.getTransaction().getStatus() == TransactionStatus.ACTIVE ) {
					session.getTransaction().rollback();
				}
				throw e;
			}
		}
		try (Session session = openSession()) {
			session.getTransaction().begin();
			try {
				session.createQuery( "DELETE FROM In" ).executeUpdate();
				session.getTransaction().commit();
			}
			catch (Exception e) {
				if ( session.getTransaction().getStatus() == TransactionStatus.ACTIVE ) {
					session.getTransaction().rollback();
				}
				throw e;
			}
		}
	}
