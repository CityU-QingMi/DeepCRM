	public void setIndexCollectionSelectorParamSpec(ParameterSpecification indexCollectionSelectorParamSpec) {
		if ( indexCollectionSelectorParamSpec == null ) {
			if ( elementType.getIndexCollectionSelectorParamSpec() != null ) {
				embeddedParameters.remove( elementType.getIndexCollectionSelectorParamSpec() );
				elementType.setIndexCollectionSelectorParamSpec( null );
			}
		}
		else {
			elementType.setIndexCollectionSelectorParamSpec( indexCollectionSelectorParamSpec );
			addEmbeddedParameter( indexCollectionSelectorParamSpec );
		}
	}
