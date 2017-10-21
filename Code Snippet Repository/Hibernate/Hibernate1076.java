	private void addInLineDirtyHandling(CtClass managedCtClass) {
		managedCtClass.addInterface( loadCtClassFromClass( SelfDirtinessTracker.class ) );

		FieldWriter.addField(
				managedCtClass,
				loadCtClassFromClass( DirtyTracker.class ),
				EnhancerConstants.TRACKER_FIELD_NAME
		);

		if ( collectCollectionFields( managedCtClass ).isEmpty() ) {
			createDirtyTrackerMethodsWithoutCollections( managedCtClass );
		}
		else {
			FieldWriter.addField(
					managedCtClass,
					loadCtClassFromClass( CollectionTracker.class ),
					EnhancerConstants.TRACKER_COLLECTION_NAME
			);
			createDirtyTrackerMethodsWithCollections( managedCtClass );
		}
	}
