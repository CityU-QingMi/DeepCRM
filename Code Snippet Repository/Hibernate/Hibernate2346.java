	private int getNumberOfParametersCoveredBy(Type[] subtypes) {
		int numberOfParameters = 0;
		for ( Type type : subtypes ) {
			if ( type.isComponentType() ) {
				numberOfParameters = numberOfParameters + getNumberOfParametersCoveredBy( ((ComponentType) type).getSubtypes() );
			}
			else {
				numberOfParameters++;
			}
		}
		return numberOfParameters;
	}
