	@Test
	public void testMaximizeWithIdEq() {
		List revs_id1 = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.addProjection( AuditEntity.revisionNumber() )
				.add(
						AuditEntity.property( "number" ).maximize()
								.add( AuditEntity.id().eq( id2 ) )
				)
				.addOrder( AuditEntity.revisionNumber().asc() )
				.getResultList();

		assert Arrays.asList( 2, 3, 4 ).equals( revs_id1 );
	}
