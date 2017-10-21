	@Test
	public void testHistoryOfId1() {
		PropertyAccessTypeEntity ver1 = new PropertyAccessTypeEntity( id1, "data" );
		PropertyAccessTypeEntity ver2 = new PropertyAccessTypeEntity( id1, "data2" );

		PropertyAccessTypeEntity rev1 = getAuditReader().find( PropertyAccessTypeEntity.class, id1, 1 );
		PropertyAccessTypeEntity rev2 = getAuditReader().find( PropertyAccessTypeEntity.class, id1, 2 );

		assert rev1.isIdSet();
		assert rev2.isIdSet();

		assert rev1.isDataSet();
		assert rev2.isDataSet();

		assert rev1.equals( ver1 );
		assert rev2.equals( ver2 );
	}
