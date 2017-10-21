		@Override
		public void afterCommit() {
			super.afterCommit();
			// Trigger commit here to let exceptions propagate to the caller.
			try {
				this.entityManager.getTransaction().commit();
			}
			catch (RuntimeException ex) {
				throw convertException(ex);
			}
		}
