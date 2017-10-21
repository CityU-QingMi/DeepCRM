	@Test
	public void testOwning1History() {
		// objects
		final OneToManyOwned oneToMany = new OneToManyOwned( 1L, "data", null );
		final ManyToOneOwned manyToOne = new ManyToOneOwned( 2L, "data1" );

		// insert revision
		final ManyToManyCompositeKey rev1 = getAuditReader().find( ManyToManyCompositeKey.class, owning1Id, 1 );
		assertEquals( rev1.getOneToMany(), oneToMany );
		assertEquals( rev1.getManyToOne(), manyToOne );

		// removal revision - find returns null for deleted
		assertNull( getAuditReader().find( ManyToManyCompositeKey.class, owning1Id, 2 ) );

		// fetch revision 2 using 'select deletions' api and verify.
		final ManyToManyCompositeKey rev2 = (ManyToManyCompositeKey) getAuditReader()
				.createQuery()
				.forRevisionsOfEntity( ManyToManyCompositeKey.class, true, true )
				.add( AuditEntity.id().eq( owning1Id ) )
				.add( AuditEntity.revisionNumber().eq( 2 ) )
				.getSingleResult();
		assertEquals( rev2.getOneToMany(), oneToMany );
		assertEquals( rev2.getManyToOne(), manyToOne );
	}
