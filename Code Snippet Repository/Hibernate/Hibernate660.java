	@Override
	public AnnotatedClassType getClassType(XClass clazz) {
		AnnotatedClassType type = annotatedClassTypeMap.get( clazz.getName() );
		if ( type == null ) {
			return addClassType( clazz );
		}
		else {
			return type;
		}
	}
