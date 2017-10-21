	@Override
	protected void applyAcknowledgeMode(BeanWrapper bw, int ackMode) {
		if (ackMode == Session.SESSION_TRANSACTED && bw.isWritableProperty("useRAManagedTransaction")) {
			// ActiveMQ
			bw.setPropertyValue("useRAManagedTransaction", "true");
		}
		else {
			super.applyAcknowledgeMode(bw, ackMode);
		}
	}
