	protected <Y> void addAssignment(Path<Y> attributePath, Expression<? extends Y> value) {
		if ( ! PathImplementor.class.isInstance( attributePath ) ) {
			throw new IllegalArgumentException( "Unexpected path implementation type : " + attributePath.getClass().getName() );
		}
		if ( ! SingularAttributePath.class.isInstance( attributePath ) ) {
			throw new IllegalArgumentException(
					"Attribute path for assignment must represent a singular attribute ["
							+ ( (PathImplementor) attributePath ).getPathIdentifier() + "]"
			);
		}
		if ( value == null ) {
			throw new IllegalArgumentException( "Assignment value expression cannot be null. Did you mean to pass null as a literal?" );
		}
		assignments.add( new Assignment<Y>( (SingularAttributePath<Y>) attributePath, value ) );
	}
