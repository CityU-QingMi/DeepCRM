	@Test
	public void testHistoryOfEdIng4() {
		SetRefEdEntity ed1 = new SetRefEdEntity( 1, "data_ed_1" );

		SetRefIngEntity rev1 = getAuditReader().find( SetRefIngEntity.class, ing4_id, 1 );
		SetRefIngEntity rev2 = getAuditReader().find( SetRefIngEntity.class, ing4_id, 2 );
		SetRefIngEntity rev3 = getAuditReader().find( SetRefIngEntity.class, ing4_id, 3 );
		SetRefIngEntity rev4 = getAuditReader().find( SetRefIngEntity.class, ing4_id, 4 );
		SetRefIngEntity rev5 = getAuditReader().find( SetRefIngEntity.class, ing4_id, 5 );

		assert rev1.getReference().equals( ed1 );
		assert rev2.getReference().equals( ed1 );
		assert rev3.getReference().equals( ed1 );
		assert rev4.getReference().equals( ed1 );
		assert rev5.getReference() == null;
	}
