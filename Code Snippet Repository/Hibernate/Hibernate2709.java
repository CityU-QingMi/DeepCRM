	@Override
	public void setExpectedType(Type expectedType) {
		if ( this.expectedType != null ) {
			return;
		}

		if ( AttributeConverterTypeAdapter.class.isInstance( expectedType ) ) {
			final AttributeConverterTypeAdapter adapterType = (AttributeConverterTypeAdapter) expectedType;
			setText( determineConvertedValue( adapterType, getLiteralValue() ) );
			this.expectedType = expectedType;
		}
	}
