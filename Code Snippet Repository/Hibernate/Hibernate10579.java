	@Test
	public void testRevisionTypes() {
		@SuppressWarnings({"unchecked"}) List<Object[]> results =
				getAuditReader().createQuery()
						.forRevisionsOfEntity( StrTestEntity.class, false, true )
						.add( AuditEntity.id().eq( id ) )
						.getResultList();

		assertEquals( results.get( 0 )[2], RevisionType.ADD );
		assertEquals( results.get( 1 )[2], RevisionType.DEL );
	}
