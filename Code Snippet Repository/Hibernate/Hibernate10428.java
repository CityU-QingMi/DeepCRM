	@Test
	public void testHistoryOfSse1() {
		EnumSetEntity rev1 = getAuditReader().find( EnumSetEntity.class, sse1_id, 1 );
		EnumSetEntity rev2 = getAuditReader().find( EnumSetEntity.class, sse1_id, 2 );
		EnumSetEntity rev3 = getAuditReader().find( EnumSetEntity.class, sse1_id, 3 );

		assert rev1.getEnums1().equals( TestTools.makeSet( E1.X ) );
		assert rev2.getEnums1().equals( TestTools.makeSet( E1.X, E1.Y ) );
		assert rev3.getEnums1().equals( TestTools.makeSet( E1.Y ) );

		assert rev1.getEnums2().equals( TestTools.makeSet( E2.A ) );
		assert rev2.getEnums2().equals( TestTools.makeSet( E2.A ) );
		assert rev3.getEnums2().equals( TestTools.makeSet( E2.A ) );
	}
