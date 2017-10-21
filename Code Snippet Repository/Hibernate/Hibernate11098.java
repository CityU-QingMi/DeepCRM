	@Test
	public void testProjectionsInEntitiesAtRevision() {
		assert getAuditReader().createQuery().forEntitiesAtRevision( StrIntTestEntity.class, 1 )
				.getResultList().size() == 2;
		assert getAuditReader().createQuery().forEntitiesAtRevision( StrIntTestEntity.class, 2 )
				.getResultList().size() == 1;

		assert (Long) getAuditReader().createQuery().forEntitiesAtRevision( StrIntTestEntity.class, 1 )
				.addProjection( AuditEntity.id().count() ).getResultList().get( 0 ) == 2;
		assert (Long) getAuditReader().createQuery().forEntitiesAtRevision( StrIntTestEntity.class, 2 )
				.addProjection( AuditEntity.id().count() ).getResultList().get( 0 ) == 1;
	}
