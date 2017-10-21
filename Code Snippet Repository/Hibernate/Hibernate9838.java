	private void searchForRevisionInfoCfg(
			XClass clazz, ReflectionManager reflectionManager,
			MutableBoolean revisionNumberFound, MutableBoolean revisionTimestampFound,
			MutableBoolean modifiedEntityNamesFound) {
		final XClass superclazz = clazz.getSuperclass();
		if ( !"java.lang.Object".equals( superclazz.getName() ) ) {
			searchForRevisionInfoCfg(
					superclazz,
					reflectionManager,
					revisionNumberFound,
					revisionTimestampFound,
					modifiedEntityNamesFound
			);
		}

		searchForRevisionInfoCfgInProperties(
				clazz, reflectionManager, revisionNumberFound, revisionTimestampFound,
				modifiedEntityNamesFound, "field"
		);
		searchForRevisionInfoCfgInProperties(
				clazz, reflectionManager, revisionNumberFound, revisionTimestampFound,
				modifiedEntityNamesFound, "property"
		);
	}
