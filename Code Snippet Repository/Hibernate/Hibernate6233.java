		@Override
		public void configure(Map configurationValues) {
			try {
				Thread.sleep( TIME_TO_SLEEP );
			}
			catch (InterruptedException e) {
			}

			configured = true;
		}
