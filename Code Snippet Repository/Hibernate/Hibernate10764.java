	@Test
	public void testModifiedFlagChangesForProjectName() {
		final List results = getAuditReader().createQuery()
				.forRevisionsOfEntity( Project.class, false, true )
				.add( AuditEntity.property( "name" ).hasChanged() )
				.addProjection( AuditEntity.revisionNumber() )
				.addOrder( AuditEntity.revisionNumber().asc() )
				.getResultList();
		assertEquals( Arrays.asList( 1, 2, 3, 4, 5, 6 ), results );
	}
