	private static boolean extendsSuperMetaModel(Element superClassElement, boolean entityMetaComplete, Context context) {
		// if we processed the superclass in the same run we definitely need to extend
		String superClassName = ( (TypeElement) superClassElement ).getQualifiedName().toString();
		if ( context.containsMetaEntity( superClassName )
				|| context.containsMetaEmbeddable( superClassName ) ) {
			return true;
		}

		// to allow for the case that the metamodel class for the super entity is for example contained in another
		// jar file we use reflection. However, we need to consider the fact that there is xml configuration
		// and annotations should be ignored
		if ( !entityMetaComplete && ( TypeUtils.containsAnnotation( superClassElement, Constants.ENTITY )
				|| TypeUtils.containsAnnotation( superClassElement, Constants.MAPPED_SUPERCLASS ) ) ) {
			return true;
		}

		return false;
	}
