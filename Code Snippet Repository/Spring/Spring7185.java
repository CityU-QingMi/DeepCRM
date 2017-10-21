	private void executeDestructionCallbacks() {
		for (Map.Entry<String, Object> entry : this.attributes.entrySet()) {
			if (entry.getKey().startsWith(DESTRUCTION_CALLBACK_NAME_PREFIX)) {
				try {
					((Runnable) entry.getValue()).run();
				}
				catch (Throwable ex) {
					logger.error("Uncaught error in session attribute destruction callback", ex);
				}
			}
		}
	}
