	@Test
	public void testHistoryOfParent() {
		ListRefCollEntity parent = new ListRefCollEntity( parentId, "initial data" );
		parent.setCollection( Arrays.asList( new StrTestEntity( "data", childId ) ) );

		ListRefCollEntity ver1 = getAuditReader().find( ListRefCollEntity.class, parentId, 1 );

		Assert.assertEquals( parent, ver1 );
		Assert.assertEquals( parent.getCollection(), ver1.getCollection() );

		parent.setData( "modified data" );

		ListRefCollEntity ver2 = getAuditReader().find( ListRefCollEntity.class, parentId, 2 );

		Assert.assertEquals( parent, ver2 );
		Assert.assertEquals( parent.getCollection(), ver2.getCollection() );
	}
