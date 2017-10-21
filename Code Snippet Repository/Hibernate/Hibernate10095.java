	public AuditProcess(RevisionInfoGenerator revisionInfoGenerator, SessionImplementor session) {
		this.revisionInfoGenerator = revisionInfoGenerator;
		this.session = session;

		workUnits = new LinkedList<>();
		undoQueue = new LinkedList<>();
		usedIds = new HashMap<>();
		entityStateCache = new HashMap<>();
		entityChangeNotifier = new EntityChangeNotifier( revisionInfoGenerator, session );
	}
