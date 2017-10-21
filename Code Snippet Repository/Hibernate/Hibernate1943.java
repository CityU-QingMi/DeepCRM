		private boolean canClearEntityEntryReference(){

			if( managedEntity.$$_hibernate_getEntityEntry() == null ) {
				return true;
			}

			if( !(managedEntity.$$_hibernate_getEntityEntry() instanceof ImmutableEntityEntry) ) {
				return true;
			}
			else if( managedEntity.$$_hibernate_getEntityEntry().getPersister().canUseReferenceCacheEntries() ) {
				return false;
			}

			return true;

		}
