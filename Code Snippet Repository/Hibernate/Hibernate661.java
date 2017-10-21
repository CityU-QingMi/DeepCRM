	@Override
	public AnnotatedClassType addClassType(XClass clazz) {
		AnnotatedClassType type;
		if ( clazz.isAnnotationPresent( Entity.class ) ) {
			type = AnnotatedClassType.ENTITY;
		}
		else if ( clazz.isAnnotationPresent( Embeddable.class ) ) {
			type = AnnotatedClassType.EMBEDDABLE;
		}
		else if ( clazz.isAnnotationPresent( javax.persistence.MappedSuperclass.class ) ) {
			type = AnnotatedClassType.EMBEDDABLE_SUPERCLASS;
		}
		else {
			type = AnnotatedClassType.NONE;
		}
		annotatedClassTypeMap.put( clazz.getName(), type );
		return type;
	}
