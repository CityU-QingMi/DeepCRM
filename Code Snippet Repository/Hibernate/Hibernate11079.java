	@Test
	public void testEntitiesAvgMaxQuery() {
		Object[] ver1 = (Object[]) getAuditReader().createQuery()
				.forEntitiesAtRevision( IntTestEntity.class, 1 )
				.addProjection( AuditEntity.property( "number" ).max() )
				.addProjection( AuditEntity.property( "number" ).function( "avg" ) )
				.getSingleResult();

		Object[] ver2 = (Object[]) getAuditReader().createQuery()
				.forEntitiesAtRevision( IntTestEntity.class, 2 )
				.addProjection( AuditEntity.property( "number" ).max() )
				.addProjection( AuditEntity.property( "number" ).function( "avg" ) )
				.getSingleResult();

		Object[] ver3 = (Object[]) getAuditReader().createQuery()
				.forEntitiesAtRevision( IntTestEntity.class, 3 )
				.addProjection( AuditEntity.property( "number" ).max() )
				.addProjection( AuditEntity.property( "number" ).function( "avg" ) )
				.getSingleResult();

		assert (Integer) ver1[0] == 10;
		assert (Double) ver1[1] == 6.0;

		assert (Integer) ver2[0] == 10;
		assert (Double) ver2[1] == 6.0;

		assert (Integer) ver3[0] == 52;
		assert (Double) ver3[1] == 20.0;
	}
