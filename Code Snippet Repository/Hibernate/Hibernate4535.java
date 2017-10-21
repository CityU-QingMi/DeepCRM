	public boolean isBound(QueryParameter parameter) {
		final QueryParameterBinding binding = locateBinding( parameter );
		if ( binding != null ) {
			return binding.getBindValue() != null;
		}

		final QueryParameterListBinding listBinding = locateQueryParameterListBinding( parameter );
		if ( listBinding != null ) {
			return listBinding.getBindValues() != null;
		}

		return false;
	}
