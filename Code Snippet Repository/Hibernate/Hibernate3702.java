	private void checkPoppedEntity(ExpandingFetchSource fetchSource, EntityDefinition entityDefinition) {
		// make sure what we just fetchSource represents entityDefinition
		if ( ! EntityReference.class.isInstance( fetchSource ) ) {
			throw new WalkingException(
					String.format(
							"Mismatched FetchSource from stack on pop.  Expecting EntityReference(%s), but found %s",
							entityDefinition.getEntityPersister().getEntityName(),
							fetchSource
					)
			);
		}

		final EntityReference entityReference = (EntityReference) fetchSource;
		// NOTE : this is not the most exhaustive of checks because of hierarchical associations (employee/manager)
		if ( ! entityReference.getEntityPersister().equals( entityDefinition.getEntityPersister() ) ) {
			throw new WalkingException( "Mismatched FetchSource from stack on pop" );
		}
	}
