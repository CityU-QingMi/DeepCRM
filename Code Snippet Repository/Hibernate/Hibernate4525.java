	@Override
	public void setBindValue(T value, Type clarifiedType) {
		if ( isBindingValidationRequired ) {
			validate( value, clarifiedType );
		}
		bindValue( value );
		if ( clarifiedType != null ) {
			this.bindType = clarifiedType;
		}
	}
