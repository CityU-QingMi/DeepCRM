	@Test
	public void testHistoryOfEdIng1() {
		SetRefEdEntity ed1 = new SetRefEdEntity( 1, "data_ed_1" );

		SetRefIngEntity rev1 = getAuditReader().find( SetRefIngEntity.class, ing1_id, 1 );
		SetRefIngEntity rev2 = getAuditReader().find( SetRefIngEntity.class, ing1_id, 2 );
		SetRefIngEntity rev3 = getAuditReader().find( SetRefIngEntity.class, ing1_id, 3 );
		SetRefIngEntity rev4 = getAuditReader().find( SetRefIngEntity.class, ing1_id, 4 );
		SetRefIngEntity rev5 = getAuditReader().find( SetRefIngEntity.class, ing1_id, 5 );

		assert rev1.getReference().equals( ed1 );
		assert rev2.getReference() == null;
		assert rev3.getReference() == null;
		assert rev4.getReference() == null;
		assert rev5.getReference() == null;
	}
