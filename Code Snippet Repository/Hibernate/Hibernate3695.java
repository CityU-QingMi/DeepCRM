	@Override
	public void startingComposite(CompositionDefinition compositionDefinition) {
		log.tracef(
				"%s Starting composite : %s",
				StringHelper.repeat( ">>", fetchSourceStack.size() ),
				compositionDefinition.getName()
		);

		if ( fetchSourceStack.isEmpty() && collectionReferenceStack.isEmpty() ) {
			throw new HibernateException( "A component cannot be the root of a walk nor a graph" );
		}

		// No need to push anything here; it should have been pushed by
		// #startingAttribute, #startingCollectionElements, #startingCollectionIndex, or #startingEntityIdentifier
		final FetchSource currentSource = currentSource();
		if ( !CompositeFetch.class.isInstance( currentSource ) &&
				!CollectionFetchableElement.class.isInstance( currentSource ) &&
				!CollectionFetchableIndex.class.isInstance( currentSource ) &&
				!ExpandingEntityIdentifierDescription.class.isInstance( currentSource ) ) {
			throw new WalkingException( "Mismatched FetchSource from stack on pop" );
		}
	}
