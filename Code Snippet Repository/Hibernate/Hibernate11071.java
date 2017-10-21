	@Test
	public void testElementCollection() {
		List queryResult = getAuditReader().createQuery().forRevisionsOfEntity( StringSetEntity.class, false, true )
				.add( AuditEntity.id().eq( stringSetId ) )
				.add( AuditEntity.revisionType().eq( RevisionType.DEL ) )
				.getResultList();
		Object[] objArray = (Object[]) queryResult.get( 0 );

		Assert.assertEquals( 12, getRevisionNumber( objArray[1] ) );

		StringSetEntity stringSetEntity = (StringSetEntity) objArray[0];
		Assert.assertEquals( TestTools.makeSet( "string 1", "string 2" ), stringSetEntity.getStrings() );
	}
