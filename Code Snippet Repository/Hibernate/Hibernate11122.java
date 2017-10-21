	@Test
	public void testEntitiesOrderLimitByQueryRev2() {
		List res_0_to_1 = getAuditReader().createQuery()
				.forEntitiesAtRevision( IntTestEntity.class, 2 )
				.addOrder( AuditEntity.property( "number" ).desc() )
				.setFirstResult( 0 )
				.setMaxResults( 2 )
				.getResultList();

		List res_2_to_3 = getAuditReader().createQuery()
				.forEntitiesAtRevision( IntTestEntity.class, 2 )
				.addOrder( AuditEntity.property( "number" ).desc() )
				.setFirstResult( 2 )
				.setMaxResults( 2 )
				.getResultList();

		List res_4 = getAuditReader().createQuery()
				.forEntitiesAtRevision( IntTestEntity.class, 2 )
				.addOrder( AuditEntity.property( "number" ).desc() )
				.setFirstResult( 4 )
				.setMaxResults( 2 )
				.getResultList();

		assert Arrays.asList( new IntTestEntity( 15, id4 ), new IntTestEntity( 8, id3 ) ).equals( res_0_to_1 );
		assert Arrays.asList( new IntTestEntity( 5, id2 ), new IntTestEntity( 3, id5 ) ).equals( res_2_to_3 );
		assert Arrays.asList( new IntTestEntity( 0, id1 ) ).equals( res_4 );
	}
