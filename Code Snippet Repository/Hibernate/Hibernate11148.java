	@Test
	public void testRevisionsPropertyEqQuery() {
		List revs_id1 = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.add( AuditEntity.id().eq( id1 ) )
				.getResultList();

		Assert.assertEquals( 2, revs_id1.size() );
		Assert.assertEquals( new StrIntTestEntity( "a", 10, id1 ), ((Object[]) revs_id1.get( 0 ))[0] );
		Assert.assertEquals( new StrIntTestEntity( "a", 10, id1 ), ((Object[]) revs_id1.get( 1 ))[0] );
	}
