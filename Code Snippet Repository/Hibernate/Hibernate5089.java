	private Class determineAssociatedEntityClass() {
		final String entityName = getAssociatedEntityName();
		try {
			return ReflectHelper.classForName( entityName );
		}
		catch (ClassNotFoundException cnfe) {
			return this.scope.resolveFactory().getMetamodel().entityPersister( entityName ).
					getEntityTuplizer().getMappedClass();
		}
	}
