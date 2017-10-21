		@Override
		protected void cleanupResource(
				EntityManagerHolder resourceHolder, EntityManagerFactory resourceKey, boolean committed) {

			if (!committed) {
				// Clear all pending inserts/updates/deletes in the EntityManager.
				// Necessary for pre-bound EntityManagers, to avoid inconsistent state.
				resourceHolder.getEntityManager().clear();
			}
			cleanupTransaction(this.transactionData, resourceKey);
		}
