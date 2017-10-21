		@Override
		public void injectServices(ServiceRegistryImplementor serviceRegistry) {
			try {
				Thread.sleep( TIME_TO_SLEEP );
			}
			catch (InterruptedException e) {
			}

			initialized = true;
		}
