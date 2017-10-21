	@Before
	public void newEntityManager() {
		closeEntityManager();

		entityManager = emf.createEntityManager();

		if ( audited ) {
			auditReader = AuditReaderFactory.get( entityManager );
		}
	}
