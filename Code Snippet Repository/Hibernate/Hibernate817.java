	private void insertMappedSuperclasses(List<XClass> original, List<XClass> copy) {
		for ( XClass clazz : original ) {
			XClass superClass = clazz.getSuperclass();
			while ( superClass != null
					&& !reflectionManager.equals( superClass, Object.class )
					&& !copy.contains( superClass ) ) {
				if ( superClass.isAnnotationPresent( Entity.class )
						|| superClass.isAnnotationPresent( javax.persistence.MappedSuperclass.class ) ) {
					copy.add( superClass );
				}
				superClass = superClass.getSuperclass();
			}
		}
	}
