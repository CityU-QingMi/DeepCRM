	@Test
	public void testRevisionHistory() {
		final AuditReader reader = getAuditReader();

		AuditQuery categoryQuery = reader.createQuery().forRevisionsOfEntity( Category.class, false, true )
				.addOrder( AuditEntity.revisionProperty( "timestamp" ).asc() )
				.add( AuditEntity.id().eq( category.getId() ) );

		@SuppressWarnings( "unchecked" )
		List<Object[]> history = (List<Object[]>) categoryQuery.getResultList();
		assertNotNull( history );
		assertEquals( 1, history.size() );

		final Category category = (Category) reader.createQuery().forEntitiesAtRevision( Category.class, 1 )
				.add( AuditEntity.property( "id" ).eq( this.category.getId() ) )
				.setMaxResults( 1 )
				.getSingleResult();

		assertEquals( this.category.getName(), category.getName() );
		assertEquals( this.category.getDescription(), category.getDescription() );
		assertEquals( "The text", category.getText( this.item ) );

		final Value value = category.getValue( this.item );
		assertEquals( "The Value", value.getText() );
		assertEquals( Long.valueOf( 4711L ), value.getNumber() );
	}
