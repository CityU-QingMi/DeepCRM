	private static void bindFilters(
			XClass annotatedClass,
			EntityBinder entityBinder,
			MetadataBuildingContext context) {

		bindFilters( annotatedClass, entityBinder );

		XClass classToProcess = annotatedClass.getSuperclass();
		while ( classToProcess != null ) {
			AnnotatedClassType classType = context.getMetadataCollector().getClassType( classToProcess );
			if ( AnnotatedClassType.EMBEDDABLE_SUPERCLASS.equals( classType ) ) {
				bindFilters( classToProcess, entityBinder );
			}
			else {
				break;
			}
			classToProcess = classToProcess.getSuperclass();
		}

	}
