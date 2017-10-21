	public void read() {
		// First reading the access types for the persistent properties.
		readPersistentPropertiesAccess();

		if ( persistentPropertiesSource instanceof DynamicComponentSource ) {
			addPropertiesFromDynamicComponent( (DynamicComponentSource) persistentPropertiesSource );
		}
		else {
			// Retrieve classes and properties that are explicitly marked for auditing process by any superclass
			// of currently mapped entity or itself.
			final XClass clazz = persistentPropertiesSource.getXClass();
			readAuditOverrides( clazz );

			// Adding all properties from the given class.
			addPropertiesFromClass( clazz );
		}
	}
