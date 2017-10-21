	private static CtField findFieldOrNull(CtClass ctClass, String propertyName) {
		if ( ctClass == null ) {
			return null;
		}
		try {
			return ctClass.getField( propertyName );
		}
		catch ( NotFoundException nsfe ) {
			try {
				return findFieldOrNull( ctClass.getSuperclass(), propertyName );
			}
			catch (NotFoundException e) {
				return null;
			}
		}
	}
