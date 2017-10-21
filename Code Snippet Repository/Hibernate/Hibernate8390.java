	@Test
	@TestForIssue(jiraKey = "")
	public void testEmbeddedFieldIsNotNull() {
		doInHibernate( this::sessionFactory, session -> {
			final ConcreteEntity entity = session.get( ConcreteEntity.class, 1L );
			assertThat( entity.getEmbedded().getField(), is( "field_embedded" ) );
			assertThat( entity.getField(), is( "field_base" ) );
			entity.getEmbedded().setField( "field_subclass" );
		} );
		doInHibernate( this::sessionFactory, session -> {
			final ConcreteEntity entity = session.get( ConcreteEntity.class, 1L );
			assertThat( entity.getEmbedded().getField(), is( "field_subclass" ) );
			assertThat( entity.getField(), is( "field_base" ) );
		} );
	}
