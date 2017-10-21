	public IdentifierProperty(
			Type type,
			boolean embedded,
			boolean hasIdentifierMapper,
			IdentifierValue unsavedValue,
			IdentifierGenerator identifierGenerator) {
		super( null, type );
		this.virtual = true;
		this.embedded = embedded;
		this.hasIdentifierMapper = hasIdentifierMapper;
		this.unsavedValue = unsavedValue;
		this.identifierGenerator = identifierGenerator;
		this.identifierAssignedByInsert = identifierGenerator instanceof PostInsertIdentifierGenerator;
	}
