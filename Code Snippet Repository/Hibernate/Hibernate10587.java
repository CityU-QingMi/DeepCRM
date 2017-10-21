	@Test
	@Priority(10)
	public void initData() {
		EntityManager entityManager = getEntityManager();
		try {
			// Revision 1 - Create entity with 2 components
			entityManager.getTransaction().begin();
			Component component1 = new Component();
			component1.setName( "User1" );
			component1.setData( "Test1" );
			Component component2 = new Component();
			component2.setName( "User2" );
			component2.setData( "Test2" );
			ComponentEntity entity = new ComponentEntity();
			entity.getComponents().add( component1 );
			entity.getComponents().add( component2 );
			entityManager.persist( entity );
			entityManager.getTransaction().commit();
			id = entity.getId();

			// Revision 2 - Change component name inline.
			// This effectively changes equality and hash of elment.
			entityManager.getTransaction().begin();
			component1.setName( "User1-Inline" );
			entityManager.merge( entity );
			entityManager.getTransaction().commit();

			// Revision 3 - Remove and add entity with same name.
			entityManager.getTransaction().begin();
			entity.getComponents().remove( component2 );
			Component component3 = new Component();
			component3.setName( "User2" );
			component3.setData( "Test3" );
			entity.getComponents().add( component3 );
			entityManager.merge( entity );
			entityManager.getTransaction().commit();
		}
		catch ( Exception e ) {
			if ( entityManager.getTransaction().isActive() ) {
				entityManager.getTransaction().rollback();
			}
		}
		finally {
			entityManager.close();
		}
	}
