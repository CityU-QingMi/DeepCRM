	@Test
	public void testHasChangedHasNotChangedCriteria() throws Exception {
		List list = getAuditReader().createQuery().forRevisionsOfEntity( AuditedTestEntity.class, true, true )
				.add( AuditEntity.property( "str1" ).hasChanged() ).getResultList();
		assertEquals( 2, list.size() );
		assertEquals( "str1", ((AuditedTestEntity) list.get( 0 )).getStr1() );
		assertEquals( "str2", ((AuditedTestEntity) list.get( 1 )).getStr1() );

		list = getAuditReader().createQuery().forRevisionsOfEntity( AuditedTestEntity.class, true, true )
				.add( AuditEntity.property( "str1" ).hasNotChanged() ).getResultList();
		assertTrue( list.isEmpty() );
	}
