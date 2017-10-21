	@Test
	public void testHistoryOfCcte() {
		CompositeCustomTypeEntity rev1 = getAuditReader().find( CompositeCustomTypeEntity.class, ccte_id, 1 );
		CompositeCustomTypeEntity rev2 = getAuditReader().find( CompositeCustomTypeEntity.class, ccte_id, 2 );
		CompositeCustomTypeEntity rev3 = getAuditReader().find( CompositeCustomTypeEntity.class, ccte_id, 3 );

		assert rev1.getComponent().equals( new Component( "a", 1 ) );
		assert rev2.getComponent().equals( new Component( "b", 1 ) );
		assert rev3.getComponent().equals( new Component( "c", 3 ) );
	}
