	public void setSessionContext(SessionAttributesHandler attributesHandler, WebSession session) {
		this.saveModelOperation = () -> {
			if (getSessionStatus().isComplete()) {
				attributesHandler.cleanupAttributes(session);
			}
			else {
				attributesHandler.storeAttributes(session, getModel().asMap());
			}
		};
	}
