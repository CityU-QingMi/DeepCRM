	public TransientPropertyValueException(
			String message, 
			String transientEntityName, 
			String propertyOwnerEntityName, 
			String propertyName) {
		super( message );
		this.transientEntityName = transientEntityName;
		this.propertyOwnerEntityName = propertyOwnerEntityName;
		this.propertyName = propertyName;
	}
