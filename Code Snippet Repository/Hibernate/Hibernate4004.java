	public static boolean isManyToMany(Member member) {
		if ( Field.class.isInstance( member ) ) {
			return ( (Field) member ).getAnnotation( ManyToMany.class ) != null;
		}
		else if ( Method.class.isInstance( member ) ) {
			return ( (Method) member ).getAnnotation( ManyToMany.class ) != null;
		}

		return false;
	}
