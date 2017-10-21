	private Set<String> processPersistentClassHierarchy(
			PersistentClass persistentClass,
			boolean isBase,
			SessionFactoryImplementor factory,
			String[][] mapping) {

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// collect all the class names that indicate that the "main table" of the given PersistentClass should be
		// included when one of the collected class names is used in TREAT
		final Set<String> classNames = new HashSet<String>();

		final Iterator itr = persistentClass.getDirectSubclasses();
		while ( itr.hasNext() ) {
			final Subclass subclass = (Subclass) itr.next();
			final Set<String> subclassSubclassNames = processPersistentClassHierarchy(
					subclass,
					false,
					factory,
					mapping
			);
			classNames.addAll( subclassSubclassNames );
		}

		classNames.add( persistentClass.getEntityName() );

		if ( ! isBase ) {
			MappedSuperclass msc = persistentClass.getSuperMappedSuperclass();
			while ( msc != null ) {
				classNames.add( msc.getMappedClass().getName() );
				msc = msc.getSuperMappedSuperclass();
			}

			associateSubclassNamesToSubclassTableIndexes( persistentClass, classNames, mapping, factory );
		}

		return classNames;
	}
