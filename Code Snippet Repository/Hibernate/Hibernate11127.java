	@Test
	public void testRevisionProjectionQuery() {
		Object[] result = (Object[]) getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.addProjection( AuditEntity.revisionNumber().max() )
				.addProjection( AuditEntity.revisionNumber().count() )
				.addProjection( AuditEntity.revisionNumber().countDistinct() )
				.addProjection( AuditEntity.revisionNumber().min() )
				.add( AuditEntity.id().eq( id1 ) )
				.getSingleResult();

		Assert.assertEquals( Integer.valueOf( 4 ), result[0] );
		Assert.assertEquals( Long.valueOf( 4 ), result[1] );
		Assert.assertEquals( Long.valueOf( 4 ), result[2] );
		Assert.assertEquals( Integer.valueOf( 1 ), result[3] );
	}
