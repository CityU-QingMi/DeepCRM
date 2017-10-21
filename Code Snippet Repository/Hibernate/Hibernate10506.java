	@Test
	public void shouldFailWhenQueryOnManyToOne() {
		//when
		PlainEntity plainEntity = (PlainEntity) getAuditReader().createQuery()
				.forEntitiesAtRevision( PlainEntity.class, 1 )
				.add( AuditEntity.relatedId( "component_manyToOneEntity" ).eq( getManyToOneEntity().getId() ) )
				.getResultList().get( 0 );

		//then
		Assert.assertEquals( getManyToOneEntity(), plainEntity.getComponent().getManyToOneEntity() );
	}
