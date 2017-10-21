	@Test
	public void testAuditEnumMapCollection() {
		EnumMapEntity rev1 = getAuditReader().find( EnumMapEntity.class, entityId, 1 );
		assertTrue( rev1.getTypes().keySet().containsAll(
				Arrays.asList( EnumMapEntity.EnumType.TYPE_A, EnumMapEntity.EnumType.TYPE_B )
		) );
		EnumMapEntity rev2 = getAuditReader().find( EnumMapEntity.class, entityId, 2 );
		assertTrue( rev2.getTypes().keySet().containsAll(
				Arrays.asList( EnumMapEntity.EnumType.TYPE_B, EnumMapEntity.EnumType.TYPE_C )
		) );
	}
