		protected void performAnyNeededCrossReferenceSynchronizations() {
			if ( !synchronizationEnabled ) {
				// synchronization (this process) was disabled
				return;
			}
			if ( entityPersister.getEntityMetamodel().hasImmutableNaturalId() ) {
				// only mutable natural-ids need this processing
				return;
			}
			if ( !isTransactionInProgress() ) {
				// not in a transaction so skip synchronization
				return;
			}

			final boolean debugEnabled = log.isDebugEnabled();
			for ( Serializable pk : getPersistenceContext().getNaturalIdHelper()
					.getCachedPkResolutions( entityPersister ) ) {
				final EntityKey entityKey = generateEntityKey( pk, entityPersister );
				final Object entity = getPersistenceContext().getEntity( entityKey );
				final EntityEntry entry = getPersistenceContext().getEntry( entity );

				if ( entry == null ) {
					if ( debugEnabled ) {
						log.debug(
								"Cached natural-id/pk resolution linked to null EntityEntry in persistence context : "
										+ MessageHelper.infoString( entityPersister, pk, getFactory() )
						);
					}
					continue;
				}

				if ( !entry.requiresDirtyCheck( entity ) ) {
					continue;
				}

				// MANAGED is the only status we care about here...
				if ( entry.getStatus() != Status.MANAGED ) {
					continue;
				}

				getPersistenceContext().getNaturalIdHelper().handleSynchronization(
						entityPersister,
						pk,
						entity
				);
			}
		}
