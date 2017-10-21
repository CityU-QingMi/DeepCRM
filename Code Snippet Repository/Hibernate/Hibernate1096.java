	private static String inferTypeName(CtClass ctClass, String attributeName ) {
		AccessType classAccessType = getAccessTypeOrNull( ctClass );
		CtField field = findFieldOrNull( ctClass, attributeName );
		CtMethod getter = findGetterOrNull( ctClass, attributeName );

		if ( classAccessType == AccessType.FIELD || ( field != null && getAccessTypeOrNull( field ) == AccessType.FIELD ) ) {
			return field == null ? null : inferFieldTypeName( field );
		}
		if ( classAccessType == AccessType.PROPERTY || ( getter != null && getAccessTypeOrNull( getter ) == AccessType.PROPERTY ) ) {
			return getter == null ? null : inferMethodTypeName( getter );
		}

		String found = ( getter == null ? null : inferMethodTypeName( getter ) );
		if ( found == null && field != null ) {
			return inferFieldTypeName( field );
		}
		return found;
	}
