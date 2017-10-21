	@Test
	public void testHistoryOfCollectionEntity() {
		// Revision 1
		JoinTableEntity collectionEntity = new JoinTableEntity( collectionEntityId, "some data" );
		StrTestEntity element1 = new StrTestEntity( "str1", element1Id );
		StrTestEntity element2 = new StrTestEntity( "str2", element2Id );
		collectionEntity.getReferences().add( element1 );
		collectionEntity.getReferences().add( element2 );
		JoinTableEntity ver1 = getAuditReader().find( JoinTableEntity.class, collectionEntityId, 1 );
		Assert.assertEquals( collectionEntity, ver1 );
		Assert.assertEquals( collectionEntity.getReferences(), ver1.getReferences() );

		// Revision 2
		collectionEntity.setData( "some other data" );
		JoinTableEntity ver2 = getAuditReader().find( JoinTableEntity.class, collectionEntityId, 2 );
		Assert.assertEquals( collectionEntity, ver2 );
		Assert.assertEquals( collectionEntity.getReferences(), ver2.getReferences() );

		// Revision 3
		collectionEntity.getReferences().remove( element1 );
		JoinTableEntity ver3 = getAuditReader().find( JoinTableEntity.class, collectionEntityId, 3 );
		Assert.assertEquals( collectionEntity, ver3 );
		Assert.assertEquals( collectionEntity.getReferences(), ver3.getReferences() );

		// Revision 4
		collectionEntity.setReferences( new HashSet<StrTestEntity>() );
		JoinTableEntity ver4 = getAuditReader().find( JoinTableEntity.class, collectionEntityId, 4 );
		Assert.assertEquals( collectionEntity, ver4 );
		Assert.assertEquals( collectionEntity.getReferences(), ver4.getReferences() );

		// Revision 5
		collectionEntity.getReferences().add( element1 );
		JoinTableEntity ver5 = getAuditReader().find( JoinTableEntity.class, collectionEntityId, 5 );
		Assert.assertEquals( collectionEntity, ver5 );
		Assert.assertEquals( collectionEntity.getReferences(), ver5.getReferences() );
	}
