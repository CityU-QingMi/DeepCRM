	public WrongClassException(String message, Serializable identifier, String entityName) {
		super(
				String.format(
						"Object [id=%s] was not of the specified subclass [%s] : %s",
						identifier,
						entityName,
						message
				)
		);
		this.identifier = identifier;
		this.entityName = entityName;
	}
