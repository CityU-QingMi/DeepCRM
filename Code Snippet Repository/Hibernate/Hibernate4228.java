	@Override
	public void bindValue(T value, TemporalType explicitTemporalType) {
		validateBindability();
		if ( explicitTemporalType != null ) {
			if ( ! isDateTimeType() ) {
				throw new IllegalArgumentException( "TemporalType should not be specified for non date/time type" );
			}
		}
		this.bind = new ParameterBindImpl<T>( value, explicitTemporalType );
	}
