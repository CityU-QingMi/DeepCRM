	public PropertyAccessException(
			Throwable cause,
			String message,
			boolean wasSetter,
			Class persistentClass,
			String propertyName) {
		super( message, cause );
		this.persistentClass = persistentClass;
		this.wasSetter = wasSetter;
		this.propertyName = propertyName;
	}
