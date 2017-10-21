	@Test
	public void testUpdateEntityWithPackageNameStartingWithIn() {
		Any entity = new Any();
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
				final Query query = session.createQuery( "UPDATE Any set prop = :prop WHERE id = :id " );
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
				session.createQuery( "DELETE FROM Any" ).executeUpdate();
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
