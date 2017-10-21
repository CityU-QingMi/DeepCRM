	@Override
	public EntityPersister getSubclassEntityPersister(Object instance, SessionFactoryImplementor factory) {
		if ( !hasSubclasses() ) {
			return this;
		}
		else {
			final String concreteEntityName = getEntityTuplizer().determineConcreteSubclassEntityName(
					instance,
					factory
			);
			if ( concreteEntityName == null || getEntityName().equals( concreteEntityName ) ) {
				// the contract of EntityTuplizer.determineConcreteSubclassEntityName says that returning null
				// is an indication that the specified entity-name (this.getEntityName) should be used.
				return this;
			}
			else {
				return factory.getEntityPersister( concreteEntityName );
			}
		}
	}
