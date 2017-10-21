	@Override
	public void finishingComposite(CompositionDefinition compositionDefinition) {
		// No need to pop anything here; it will be popped by
		// #finishingAttribute, #finishingCollectionElements, #finishingCollectionIndex, or #finishingEntityIdentifier

		log.tracef(
				"%s Finishing composite : %s",
				StringHelper.repeat( "<<", fetchSourceStack.size() ),
				compositionDefinition.getName()
		);
	}
