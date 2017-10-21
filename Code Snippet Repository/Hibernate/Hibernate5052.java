	private boolean determineIfProcedureParamExtractionCanBePerformed() {
		for ( Type propertyType : propertyTypes ) {
			if ( !ProcedureParameterExtractionAware.class.isInstance( propertyType ) ) {
				return false;
			}
			if ( !( (ProcedureParameterExtractionAware) propertyType ).canDoExtraction() ) {
				return false;
			}
		}
		return true;
	}
