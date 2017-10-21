	@Test
	public void testOwning2History() {
		// objects
		final OneToManyOwned oneToMany = new OneToManyOwned( 1L, "data", null );
		final ManyToOneOwned manyToOne = new ManyToOneOwned( 3L, "data2" );

		// insert revision
		final ManyToManyCompositeKey rev1 = getAuditReader().find( ManyToManyCompositeKey.class, owning2Id, 1 );
		assertEquals( rev1.getOneToMany(), oneToMany );
		assertEquals( rev1.getManyToOne(), manyToOne );

		// removal revision - find returns null for deleted
		assertNull( getAuditReader().find( ManyToManyCompositeKey.class, owning2Id, 3 ) );

		// fetch revision 3 using 'select deletions' api and verify.
		final ManyToManyCompositeKey rev2 = (ManyToManyCompositeKey) getAuditReader()
				.createQuery()
				.forRevisionsOfEntity( ManyToManyCompositeKey.class, true, true )
				.add( AuditEntity.id().eq( owning2Id ) )
				.add( AuditEntity.revisionNumber().eq( 3 ) )
				.getSingleResult();
		assertEquals( rev2.getOneToMany(), oneToMany );
		assertEquals( rev2.getManyToOne(), manyToOne );
	}
