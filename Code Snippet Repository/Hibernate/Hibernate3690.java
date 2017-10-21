	@Override
	public void foundCircularAssociation(AssociationAttributeDefinition attributeDefinition) {
		final FetchStrategy fetchStrategy = determineFetchStrategy( attributeDefinition );
		if ( fetchStrategy.getStyle() != FetchStyle.JOIN ) {
			return; // nothing to do
		}

		// Bi-directional association & the owning side was already visited.  If the current attribute node refers
		// to it, fetch.
		// ENTITY nature handled by super.
		final GraphNodeImplementor graphNode = graphStack.peekLast();
		if ( attributeDefinition.getAssociationNature() == AssociationAttributeDefinition.AssociationNature.COLLECTION
				&& ! graphNode.equals( NON_EXIST_SUBGRAPH_NODE)
				&& graphNode.containsAttribute( attributeDefinition.getName() )) {
			currentSource().buildCollectionAttributeFetch( attributeDefinition, fetchStrategy );
		}
		
		super.foundCircularAssociation( attributeDefinition );
	}
