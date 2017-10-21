		@Override
		public void close() {
			WebSocketSession session = this.session;
			if (session != null) {
				try {
					session.close();
				}
				catch (IOException ex) {
					if (logger.isDebugEnabled()) {
						logger.debug("Failed to close session: " + session.getId(), ex);
					}
				}
			}
		}
