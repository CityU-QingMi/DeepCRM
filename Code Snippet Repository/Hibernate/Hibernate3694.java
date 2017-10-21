	@Override
	public void finishingCollectionElements(CollectionElementDefinition elementDefinition) {
		final Type elementType = elementDefinition.getType();

		if ( elementType.isAnyType() ) {
			// nothing to do because the element graph was not pushed in #startingCollectionElement..
		}
		else if ( elementType.isComponentType() || elementType.isAssociationType()) {
			// pop it from the stack
			final ExpandingFetchSource popped = popFromStack();

			// validation
			if ( ! CollectionFetchableElement.class.isInstance( popped ) ) {
				throw new WalkingException( "Mismatched FetchSource from stack on pop" );
			}
		}

		log.tracef(
				"%s Finished collection element graph : %s",
				StringHelper.repeat( "<<", fetchSourceStack.size() ),
				elementDefinition.getCollectionDefinition().getCollectionPersister().getRole()
		);
	}
