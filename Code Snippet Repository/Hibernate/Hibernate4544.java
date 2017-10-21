	public void registerParameter(ProcedureParameterImplementor parameter) {
		if ( parameters == null ) {
			parameters = new ArrayList<>();
		}
		parameters.add( parameter );

		this.hasNamed = hasNamed || parameter.getName() != null;
		if ( parameter.getPosition() != null ) {
			ordinalParamCount++;
		}
	}
