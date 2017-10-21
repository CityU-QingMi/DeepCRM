	@Test
	public void testOfQueryOnDynamicComponent() {
		//given (and result of initData()
		AuditedDynamicComponentEntity entity = new AuditedDynamicComponentEntity( 1L, "static field value" );
		entity.getCustomFields().put( "prop1", 13 );
		entity.getCustomFields().put( "prop2", 0.1f );
		entity.getCustomFields().put( "prop3", new SimpleEntity( 1L, "Very simple entity" ) );
		entity.getCustomFields().put( "prop4", true );

		//when
		List resultList = getAuditReader().createQuery()
				.forEntitiesAtRevision( AuditedDynamicComponentEntity.class, 2 )
				.add( AuditEntity.property( "customFields_prop1" ).le( 20 ) )
				.getResultList();

		//then
		Assert.assertEquals( entity, resultList.get( 0 ) );

		//when
		resultList = getAuditReader().createQuery()
				.forEntitiesAtRevision( AuditedDynamicComponentEntity.class, 2 )
				.add( AuditEntity.property( "customFields_prop3" ).eq( new SimpleEntity( 1L, "Very simple entity" ) ) )
				.getResultList();


		//then
		entity = (AuditedDynamicComponentEntity) getAuditReader().createQuery()
				.forEntitiesAtRevision( AuditedDynamicComponentEntity.class, 4 )
				.getResultList().get( 0 );
		entity.getCustomFields().put( "prop2", null );

		resultList = getAuditReader().createQuery()
				.forEntitiesAtRevision( AuditedDynamicComponentEntity.class, 5 )
				.add( AuditEntity.property( "customFields_prop2" ).isNull() )
				.getResultList();

		//then
		Assert.assertEquals( entity, resultList.get( 0 ) );
	}
