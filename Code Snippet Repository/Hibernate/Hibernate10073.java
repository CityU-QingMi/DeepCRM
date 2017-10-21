	public AuditReaderImpl(
			EnversService enversService,
			Session session,
			SessionImplementor sessionImplementor) {
		this.enversService = enversService;
		this.sessionImplementor = sessionImplementor;
		this.session = session;

		firstLevelCache = new FirstLevelCache();
		crossTypeRevisionChangesReader = new CrossTypeRevisionChangesReaderImpl( this, enversService );
	}
