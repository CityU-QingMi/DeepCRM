	private static boolean hasAnnotationsOnIdClass(XClass idClass) {
//		if(idClass.getAnnotation(Embeddable.class) != null)
//			return true;

		List<XProperty> properties = idClass.getDeclaredProperties( XClass.ACCESS_FIELD );
		for ( XProperty property : properties ) {
			if ( property.isAnnotationPresent( Column.class ) || property.isAnnotationPresent( OneToMany.class ) ||
					property.isAnnotationPresent( ManyToOne.class ) || property.isAnnotationPresent( Id.class ) ||
					property.isAnnotationPresent( GeneratedValue.class ) || property.isAnnotationPresent( OneToOne.class ) ||
					property.isAnnotationPresent( ManyToMany.class )
					) {
				return true;
			}
		}
		List<XMethod> methods = idClass.getDeclaredMethods();
		for ( XMethod method : methods ) {
			if ( method.isAnnotationPresent( Column.class ) || method.isAnnotationPresent( OneToMany.class ) ||
					method.isAnnotationPresent( ManyToOne.class ) || method.isAnnotationPresent( Id.class ) ||
					method.isAnnotationPresent( GeneratedValue.class ) || method.isAnnotationPresent( OneToOne.class ) ||
					method.isAnnotationPresent( ManyToMany.class )
					) {
				return true;
			}
		}
		return false;
	}
