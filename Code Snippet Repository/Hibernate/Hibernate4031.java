	public void popEntityWorkedOn(PersistentClass persistentClass) {
		final PersistentClass stackTop = stackOfPersistentClassesBeingProcessed.remove(
				stackOfPersistentClassesBeingProcessed.size() - 1
		);
		if ( stackTop != persistentClass ) {
			throw new AssertionFailure(
					"Inconsistent popping: "
							+ persistentClass.getEntityName() + " instead of " + stackTop.getEntityName()
			);
		}
	}
