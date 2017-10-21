	protected static AccessType getAccessType(Class<?> containerJavaType, String propertyName) {
		Field field = fieldOrNull( containerJavaType, propertyName );
		AccessType fieldAccessType = getAccessTypeOrNull( field );
		if ( fieldAccessType != null ) {
			return fieldAccessType;
		}
		AccessType methodAccessType = getAccessTypeOrNull( getterMethodOrNull( containerJavaType, propertyName ) );
		if ( methodAccessType != null ) {
			return methodAccessType;
		}
		// No @Access on property or field; check to see if containerJavaType has an explicit @Access
		AccessType classAccessType = getAccessTypeOrNull( containerJavaType );
		if ( classAccessType != null ) {
			return classAccessType;
		}
		return field != null ? AccessType.FIELD : AccessType.PROPERTY;
	}
