	private static CtMethod findGetterOrNull(CtClass ctClass, String propertyName) {
		if ( ctClass == null ) {
			return null;
		}
		CtMethod method = getterOrNull( ctClass, propertyName );
		if ( method != null ) {
			return method;
		}
		try {
			// check if extends
			method = findGetterOrNull( ctClass.getSuperclass(), propertyName );
			if ( method != null ) {
				return method;
			}
			// check if implements
			for ( CtClass interfaceCtClass : ctClass.getInterfaces() ) {
				method = getterOrNull( interfaceCtClass, propertyName );
				if ( method != null ) {
					return method;
				}
			}
		}
		catch (NotFoundException nfe) {
			// give up
		}
		return null;
	}
