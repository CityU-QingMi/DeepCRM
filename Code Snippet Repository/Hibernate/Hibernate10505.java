	@Test
	public void shouldFailWhenQueryOnManyToMany() {
		ManyToManyEntity manyToMany = getManyToManyEntity();

		//when

		List<ManyToManyEntity> manyToManyEntities = new ArrayList<ManyToManyEntity>();
		manyToManyEntities.add( manyToMany );
		try {
			getAuditReader().createQuery()
					.forEntitiesAtRevision( PlainEntity.class, 1 )
					.add( AuditEntity.property( "component_manyToManyList" ).eq( manyToManyEntities ) )
					.getResultList();
			//then
			fail( "This should have generated an AuditException" );
		}
		catch ( Exception e ) {
			assertTyping( AuditException.class, e );
			assertEquals(
					"This type of relation (org.hibernate.envers.test.integration.components.dynamic.PlainEntity.component_manyToManyList) isn't supported and can't be used in queries.",
					e.getMessage()
			);
		}
	}
