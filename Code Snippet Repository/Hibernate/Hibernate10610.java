	@Test
	@Priority(10)
	public void initData() {
		EntityManager entityManager = getOrCreateEntityManager();
		try {
			// Revision 1
			entityManager.getTransaction().begin();
			Parent parent = new Parent( "Parent" );
			parent.addIncorrectChild( 1 );
			parent.addCorrectChild( 1 );
			entityManager.persist( parent );
			for ( IncorrectChild child : parent.getIncorrectChildren() ) {
				entityManager.persist( child );
			}
			for ( CorrectChild child : parent.getCorrectChildren() ) {
				entityManager.persist( child );
			}
			entityManager.getTransaction().commit();

			// Revision 2
			entityManager.getTransaction().begin();
			for ( IncorrectChild child : parent.getIncorrectChildren() ) {
				entityManager.remove( child );
			}
			parent.getIncorrectChildren().clear();
			for( CorrectChild child : parent.getCorrectChildren() ) {
				entityManager.remove( child );
			}
			parent.getCorrectChildren().clear();
			entityManager.getTransaction().commit();

			// Revision 3
			// This fails because of referential integrity constraints without fix.
			entityManager.getTransaction().begin();
			entityManager.remove( parent );
			entityManager.getTransaction().commit();
		}
		catch ( Exception e ) {
			if ( entityManager.getTransaction().isActive() ) {
				entityManager.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			entityManager.close();
		}
	}
