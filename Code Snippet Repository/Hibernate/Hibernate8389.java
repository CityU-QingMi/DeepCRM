	@Before
	public void setUp() {
		doInHibernate( this::sessionFactory, session -> {
			ConcreteEntity entity = new ConcreteEntity();
			entity.setId( 1L );
			entity.setField( "field_base" );
			EmbeddedValue embeddedValue = new EmbeddedValue();
			embeddedValue.setField( "field_embedded" );
			entity.setEmbedded( embeddedValue );

			session.persist( entity );
		} );
	}
