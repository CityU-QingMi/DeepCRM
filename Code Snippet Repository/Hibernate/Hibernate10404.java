	private void testAuditRecordsRollbackBehavior(boolean flush, Boolean autoClear) {
		EntityManager entityManager = getEntityManager();
		try {
			if ( autoClear != null ) {
				entityManager.unwrap( SessionImpl.class ).setAutoClear( autoClear );
			}

			// persist and rollback
			entityManager.getTransaction().begin();
			IntTestEntity rollbackEntity = new IntTestEntity( 30 );
			entityManager.persist( rollbackEntity );
			if ( flush ) {
				entityManager.flush();
			}
			Integer rollbackId = rollbackEntity.getId();
			entityManager.getTransaction().rollback();

			// persist and commit
			entityManager.getTransaction().begin();
			IntTestEntity commitEntity = new IntTestEntity( 50 );
			entityManager.persist( commitEntity );
			if ( flush ) {
				entityManager.flush();
			}
			Integer commitId = commitEntity.getId();
			entityManager.getTransaction().commit();

			List<Number> revisionsForSavedClass = getAuditReader().getRevisions(
					IntTestEntity.class,
					commitId
			);
			assertEquals( "There should be one revision for inserted entity.", 1, revisionsForSavedClass.size() );

			List<Number> revisionsForRolledbackClass = getAuditReader().getRevisions(
					IntTestEntity.class,
					rollbackId
			);
			assertEquals( "There should be no revision for rolled back entity.", 0, revisionsForRolledbackClass.size() );
		}
		finally {
			entityManager.close();
		}
	}
