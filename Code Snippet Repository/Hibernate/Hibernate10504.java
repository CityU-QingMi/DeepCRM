	@Test
	public void shouldFindByInternalComponentProperty() {
		ManyToOneEntity manyToOne = getManyToOneEntity();
		ManyToManyEntity manyToMany = getManyToManyEntity();
		OneToOneEntity oneToOne = getOneToOneEntity();

		PlainEntity entity = getPlainEntity( manyToOne, manyToMany, oneToOne );


		//given (and result of shouldInitData()

		//when
		List resultList = getAuditReader().createQuery()
				.forEntitiesAtRevision( PlainEntity.class, 1 )
				.add(
						AuditEntity.property( "component_internalComponent_property" )
								.eq( entity.getComponent().getInternalComponent().getProperty() )
				)
				.getResultList();

		Assert.assertEquals( entity, resultList.get( 0 ) );
	}
