	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !( o instanceof IdentifierGeneratorDefinition ) ) {
			return false;
		}

		IdentifierGeneratorDefinition that = (IdentifierGeneratorDefinition) o;

		if ( name != null ? !name.equals( that.name ) : that.name != null ) {
			return false;
		}
		if ( parameters != null ? !parameters.equals( that.parameters ) : that.parameters != null ) {
			return false;
		}
		if ( strategy != null ? !strategy.equals( that.strategy ) : that.strategy != null ) {
			return false;
		}

		return true;
	}
