	public QueryParameterBinding getBinding(int position) {
		int positionAdjustment = 0;
		if ( !parameterMetadata.isOrdinalParametersZeroBased() ) {
			positionAdjustment = -1;
		}
		QueryParameterBinding binding = null;
		if ( parameterMetadata != null ) {
			if ( !parameterMetadata.hasPositionalParameters() ) {
				// no positional parameters, assume jpa named.
				binding = locateBinding( Integer.toString( position ) );
			}
			else {
				try {
					binding = positionalParameterBindings.get( position + positionAdjustment );
					if ( binding == null ) {
						binding = makeBinding( parameterMetadata.getQueryParameter( position ) );
						positionalParameterBindings.put( position + positionAdjustment, binding );
					}
				}
				catch (QueryParameterException e) {
					// treat this as null binding
				}
			}
		}

		if ( binding == null ) {
			throw new IllegalArgumentException( "Unknown parameter position: " + position );
		}

		return binding;
	}
