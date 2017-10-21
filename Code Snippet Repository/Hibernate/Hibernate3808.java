	private void renderEntityJoin(Join join, JoinFragment joinFragment) {
		final EntityQuerySpace rightHandSide = (EntityQuerySpace) join.getRightHandSide();

		// see if there is already aliases registered for this entity query space (collection joins)
		EntityReferenceAliases aliases = aliasResolutionContext.resolveEntityReferenceAliases( rightHandSide.getUid() );
		if ( aliases == null ) {
			aliasResolutionContext.generateEntityReferenceAliases(
					rightHandSide.getUid(),
					rightHandSide.getEntityPersister()
			);
		}

		final Joinable joinable = (Joinable) rightHandSide.getEntityPersister();
		addJoins(
				join,
				joinFragment,
				joinable,
				null
		);
	}
