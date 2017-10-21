		protected final Serializable resolveNaturalId(Map<String, Object> naturalIdParameters) {
			performAnyNeededCrossReferenceSynchronizations();

			final ResolveNaturalIdEvent event =
					new ResolveNaturalIdEvent( naturalIdParameters, entityPersister, SessionImpl.this );
			fireResolveNaturalId( event );

			if ( event.getEntityId() == PersistenceContext.NaturalIdHelper.INVALID_NATURAL_ID_REFERENCE ) {
				return null;
			}
			else {
				return event.getEntityId();
			}
		}
