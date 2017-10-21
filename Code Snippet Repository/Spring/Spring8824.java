	private void closeInteraction(@Nullable Interaction interaction) {
		if (interaction != null) {
			try {
				interaction.close();
			}
			catch (ResourceException ex) {
				logger.trace("Could not close CCI Interaction", ex);
			}
			catch (Throwable ex) {
				// We don't trust the CCI driver: It might throw RuntimeException or Error.
				logger.trace("Unexpected exception on closing CCI Interaction", ex);
			}
		}
	}
