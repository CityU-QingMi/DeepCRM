	@Test
	public void testHistoryOfId1() {
		MixedAccessTypeEntity ver1 = new MixedAccessTypeEntity( id1, "data" );
		MixedAccessTypeEntity ver2 = new MixedAccessTypeEntity( id1, "data2" );

		MixedAccessTypeEntity rev1 = getAuditReader().find( MixedAccessTypeEntity.class, id1, 1 );
		MixedAccessTypeEntity rev2 = getAuditReader().find( MixedAccessTypeEntity.class, id1, 2 );

		assert rev1.isDataSet();
		assert rev2.isDataSet();

		assert rev1.equals( ver1 );
		assert rev2.equals( ver2 );
	}
