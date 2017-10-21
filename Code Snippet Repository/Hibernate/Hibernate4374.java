	private <T> ExplicitParameterInfo resolveParameterInfo(Parameter<T> param) {
		if ( ExplicitParameterInfo.class.isInstance( param ) ) {
			return (ExplicitParameterInfo) param;
		}
		else if ( ParameterExpression.class.isInstance( param ) ) {
			return explicitParameterInfoMap.get( (ParameterExpression) param );
		}
		else {
			for ( ExplicitParameterInfo parameterInfo : explicitParameterInfoMap.values() ) {
				if ( param.getName() != null && param.getName().equals( parameterInfo.getName() ) ) {
					return parameterInfo;
				}
				else if ( param.getPosition() != null && param.getPosition().equals( parameterInfo.getPosition() ) ) {
					return parameterInfo;
				}
			}
		}
		throw new IllegalArgumentException( "Unable to locate parameter [" + param + "] in query" );
	}
