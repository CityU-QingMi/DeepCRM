	private String[][] buildSubclassNamesBySubclassTableMapping(PersistentClass persistentClass, SessionFactoryImplementor factory) {
		// this value represents the number of subclasses (and not the class itself)
		final int numberOfSubclassTables = subclassTableNameClosure.length - coreTableSpan;
		if ( numberOfSubclassTables == 0 ) {
			return new String[0][];
		}

		final String[][] mapping = new String[numberOfSubclassTables][];
		processPersistentClassHierarchy( persistentClass, true, factory, mapping );
		return mapping;
	}
