	@Override
	public void associationKeyRegistered(AssociationKey associationKey) {
		// todo : use this information to maintain a map of AssociationKey->FetchSource mappings (associationKey + current FetchSource stack entry)
		//		that mapping can then be used in #foundCircularAssociationKey to build the proper BiDirectionalEntityFetch
		//		based on the mapped owner
		log.tracef(
				"%s Registering AssociationKey : %s -> %s",
				StringHelper.repeat( "..", fetchSourceStack.size() ),
				associationKey,
				currentSource()
		);
		fetchedAssociationKeySourceMap.put( associationKey, currentSource() );
	}
