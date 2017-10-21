	public void addSubclass(Subclass subclass) throws MappingException {
		// inheritance cycle detection (paranoid check)
		PersistentClass superclass = getSuperclass();
		while ( superclass != null ) {
			if ( subclass.getEntityName().equals( superclass.getEntityName() ) ) {
				throw new MappingException(
						"Circular inheritance mapping detected: " +
								subclass.getEntityName() +
								" will have it self as superclass when extending " +
								getEntityName()
				);
			}
			superclass = superclass.getSuperclass();
		}
		subclasses.add( subclass );
	}
