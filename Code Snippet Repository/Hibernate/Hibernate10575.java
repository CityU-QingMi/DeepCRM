	@Test
	@TestForIssue(jiraKey = "")
	public void testSingleRevisionInTransaction() {
		EntityManager em = getEntityManager();

		em.getTransaction().begin();

		SequenceIdRevisionEntity revisionBeforeFlush = getAuditReader().getCurrentRevision( SequenceIdRevisionEntity.class, true );
		int revisionNumberBeforeFlush = revisionBeforeFlush.getId();

		em.flush();

		StrTestEntity entity = new StrTestEntity( "entity" );
		em.persist( entity );

		em.getTransaction().commit();

		SequenceIdRevisionEntity entity2Revision = (SequenceIdRevisionEntity) ( (Object[]) getAuditReader().createQuery()
				.forRevisionsOfEntity( StrTestEntity.class, false, false ).add( AuditEntity.id().eq( entity.getId() ) ).getSingleResult() )[1];

		assertEquals(
				"The revision number obtained before the flush and the persisting of the entity should be the same as the revision number of the entity because there should only be one revision number per transaction",
				revisionNumberBeforeFlush,
				entity2Revision.getId() );

	}
