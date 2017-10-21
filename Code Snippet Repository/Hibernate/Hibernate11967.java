	private void cleanUpTest(String pckg) {
		Session session = null;
		Transaction tx = null;
		try {
			session = openSession();
			tx = session.beginTransaction();
			String hql = String.format( "delete from org.hibernate.spatial.integration.%s.GeomEntity", pckg );
			Query q = session.createQuery( hql );
			q.executeUpdate();
			tx.commit();
		}
		catch (Exception e) {
			if ( tx != null ) {
				tx.rollback();
			}
		}
		finally {
			if ( session != null ) {
				session.close();
			}
		}
	}
