	private static AccessType getAccessTypeOrNull(CtClass ctClass) {
		try {
			if ( ctClass.hasAnnotation( Access.class ) ) {
				return ( (Access) ctClass.getAnnotation( Access.class ) ).value();
			}
			else {
				CtClass extendsClass = ctClass.getSuperclass();
				return extendsClass == null ? null : getAccessTypeOrNull( extendsClass );
			}
		}
		catch (ClassNotFoundException e) {
			return null;
		}
		catch (NotFoundException e) {
			return null;
		}
	}
