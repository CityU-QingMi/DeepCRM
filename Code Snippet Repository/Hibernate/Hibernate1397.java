	private void validateBind() {
		if ( property.isAnnotationPresent( Immutable.class ) ) {
			throw new AnnotationException(
					"@Immutable on property not allowed. " +
							"Only allowed on entity level or on a collection."
			);
		}
		if ( !declaringClassSet ) {
			throw new AssertionFailure( "declaringClass has not been set before a bind" );
		}
	}
