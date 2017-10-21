	@Test
	@Priority(10)
	public void initData() {
		EntityManager entityManager = getEntityManager();
		try {
			ChildEntity childEntity = new ChildEntity( 1, "Child" );
			entityManager.getTransaction().begin();
			entityManager.persist( childEntity );
			entityManager.getTransaction().commit();

			ChildListHolder holder = new ChildListHolder();
			holder.setId( 1 );
			holder.setChildren( Arrays.asList( childEntity ) );
			entityManager.getTransaction().begin();
			entityManager.persist( holder );
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
