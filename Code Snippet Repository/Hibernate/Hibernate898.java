	private String extractIdClassName(IdentifierSourceNonAggregatedComposite identifierSource) {
		if ( identifierSource.getIdClassSource() == null ) {
			return null;
		}

		if ( identifierSource.getIdClassSource().getTypeDescriptor() == null ) {
			return null;
		}

		return identifierSource.getIdClassSource().getTypeDescriptor().getName();
	}
