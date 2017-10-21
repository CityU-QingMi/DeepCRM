	@Test
	@Priority(10)
	public void initData() {
		EntityManager entityManager = getEntityManager();
		try {
			// Revision 1
			entityManager.getTransaction().begin();

			// store refing with null refed entity
			BiRefingOptionalEntity refingWithNoRefed = new BiRefingOptionalEntity();
			refingWithNoRefed.setReference( null );
			entityManager.persist( refingWithNoRefed );

			// store refing with non-null refed entity
			BiRefingOptionalEntity refing = new BiRefingOptionalEntity();
			BiRefedOptionalEntity refed = new BiRefedOptionalEntity();
			refed.getReferences().add( refing );
			refing.setReference( refed );
			entityManager.persist( refing );
			entityManager.persist( refed );

			entityManager.getTransaction().commit();

			this.refingId = refing.getId();
			this.refedId = refed.getId();
			this.refingWithNoRefedId = refingWithNoRefed.getId();
		}
		finally {
			entityManager.close();
		}
	}
