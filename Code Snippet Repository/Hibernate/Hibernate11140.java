	@Test
	public void testRevisionsPropertyEqQuery() {
		List revs_id1 = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.addProjection( AuditEntity.revisionNumber() )
				.add( AuditEntity.property( "str1" ).le( "a" ) )
				.add( AuditEntity.id().eq( id1 ) )
				.getResultList();

		List revs_id2 = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.addProjection( AuditEntity.revisionNumber() )
				.add( AuditEntity.property( "str1" ).le( "a" ) )
				.add( AuditEntity.id().eq( id2 ) )
				.addOrder( AuditEntity.revisionNumber().asc() )
				.getResultList();

		List revs_id3 = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.addProjection( AuditEntity.revisionNumber() )
				.add( AuditEntity.property( "str1" ).le( "a" ) )
				.add( AuditEntity.id().eq( id3 ) )
				.getResultList();

		assert Arrays.asList( 1 ).equals( revs_id1 );
		assert Arrays.asList( 1, 2 ).equals( revs_id2 );
		assert Arrays.asList( 3 ).equals( revs_id3 );
	}
