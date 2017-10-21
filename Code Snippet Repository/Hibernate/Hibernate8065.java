	@Before
	public void setup() {
		AnEntity anEntity = new AnEntity();
		anEntity.description = "A very long, boring description.";

		doInHibernate(
				this::sessionFactory, session -> {
					session.persist( anEntity );
				}
		);
	}
