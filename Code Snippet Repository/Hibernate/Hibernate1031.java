	private BiDirectionalAssociationHandler(
			Implementation delegate,
			TypeDescription targetEntity,
			TypeDescription targetType,
			String mappedBy) {
		this.delegate = delegate;
		this.targetEntity = targetEntity;
		this.targetType = targetType;
		this.mappedBy = mappedBy;
	}
