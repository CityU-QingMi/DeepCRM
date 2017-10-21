	@Test
	public void testModifiedFlagChangesForProjectType() {
		final List results = getAuditReader().createQuery()
				.forRevisionsOfEntity( Project.class, false, true )
				.add( AuditEntity.property( "type" ).hasChanged() )
				.addProjection( AuditEntity.revisionNumber() )
				.addOrder( AuditEntity.revisionNumber().asc() )
				.getResultList();
		assertEquals( Arrays.asList( 1 ), results );
	}
