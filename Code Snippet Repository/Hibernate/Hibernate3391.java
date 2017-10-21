	private static void getListeners(XClass currentClazz, List<Class> orderedListeners) {
		EntityListeners entityListeners = currentClazz.getAnnotation( EntityListeners.class );
		if ( entityListeners != null ) {
			Class[] classes = entityListeners.value();
			int size = classes.length;
			for ( int index = size - 1; index >= 0; index-- ) {
				orderedListeners.add( classes[index] );
			}
		}
		if ( useAnnotationAnnotatedByListener ) {
			Annotation[] annotations = currentClazz.getAnnotations();
			for ( Annotation annot : annotations ) {
				entityListeners = annot.getClass().getAnnotation( EntityListeners.class );
				if ( entityListeners != null ) {
					Class[] classes = entityListeners.value();
					int size = classes.length;
					for ( int index = size - 1; index >= 0; index-- ) {
						orderedListeners.add( classes[index] );
					}
				}
			}
		}
	}
