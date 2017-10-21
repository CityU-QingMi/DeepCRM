	private static String findMappedSuperClass(MetaEntity entity, Context context) {
		TypeMirror superClass = entity.getTypeElement().getSuperclass();
		//superclass of Object is of NoType which returns some other kind
		while ( superClass.getKind() == TypeKind.DECLARED ) {
			//F..king Ch...t Have those people used their horrible APIs even once?
			final Element superClassElement = ( (DeclaredType) superClass ).asElement();
			String superClassName = ( (TypeElement) superClassElement ).getQualifiedName().toString();
			if ( extendsSuperMetaModel( superClassElement, entity.isMetaComplete(), context ) ) {
				return superClassName;
			}
			superClass = ( (TypeElement) superClassElement ).getSuperclass();
		}
		return null;
	}
