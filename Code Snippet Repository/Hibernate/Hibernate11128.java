	@Test
	public void testRevisionOrderQuery() {
		List result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.addProjection( AuditEntity.revisionNumber() )
				.add( AuditEntity.id().eq( id1 ) )
				.addOrder( AuditEntity.revisionNumber().desc() )
				.getResultList();

		Assert.assertEquals( Arrays.asList( 4, 3, 2, 1 ), result );
	}
