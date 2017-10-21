	private void visitIdentifierDefinition(EntityIdentifierDefinition identifierDefinition) {
		strategy.startingEntityIdentifier( identifierDefinition );

		// to make encapsulated and non-encapsulated composite identifiers work the same here, we "cheat" here a
		// little bit and simply walk the attributes of the composite id in both cases.

		// this works because the LoadPlans already build the top-level composite for composite ids

		if ( identifierDefinition.isEncapsulated() ) {
			// in the encapsulated composite id case that means we have a little bit of duplication between here and
			// visitCompositeDefinition, but in the spirit of consistently handling composite ids, that is much better
			// solution...
			final EncapsulatedEntityIdentifierDefinition idAsEncapsulated = (EncapsulatedEntityIdentifierDefinition) identifierDefinition;
			final AttributeDefinition idAttr = idAsEncapsulated.getAttributeDefinition();
			if ( CompositionDefinition.class.isInstance( idAttr ) ) {
				visitCompositeDefinition( (CompositionDefinition) idAttr );
			}
		}
		else {
			// NonEncapsulatedEntityIdentifierDefinition itself is defined as a CompositionDefinition
			visitCompositeDefinition( (NonEncapsulatedEntityIdentifierDefinition) identifierDefinition );
		}

		strategy.finishingEntityIdentifier( identifierDefinition );
	}
