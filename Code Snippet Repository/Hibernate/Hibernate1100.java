	public static boolean isAssignable(CtClass thisCtClass, String targetClassName) {
		if ( thisCtClass == null ) {
			return false;
		}
		if ( thisCtClass.getName().equals( targetClassName ) ) {
			return true;
		}

		try {
			// check if extends
			if ( isAssignable( thisCtClass.getSuperclass(), targetClassName ) ) {
				return true;
			}
			// check if implements
			for ( CtClass interfaceCtClass : thisCtClass.getInterfaces() ) {
				if ( isAssignable( interfaceCtClass, targetClassName ) ) {
					return true;
				}
			}
		}
		catch (NotFoundException e) {
			// keep going
		}
		return false;
	}
