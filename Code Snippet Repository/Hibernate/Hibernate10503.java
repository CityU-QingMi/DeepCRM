	@Test
	public void shouldFindRevisionBySimpleProperty() {
		ManyToOneEntity manyToOne = getManyToOneEntity();
		ManyToManyEntity manyToMany = getManyToManyEntity();
		OneToOneEntity oneToOne = getOneToOneEntity();

		PlainEntity entity = getPlainEntity( manyToOne, manyToMany, oneToOne );


		//given (and result of shouldInitData()

		//when
		List resultList = getAuditReader().createQuery()
				.forEntitiesAtRevision( PlainEntity.class, 1 )
				.add( AuditEntity.property( "component_componentNote" ).eq( "Note" ) )
				.getResultList();

		Assert.assertEquals( entity, resultList.get( 0 ) );
	}
