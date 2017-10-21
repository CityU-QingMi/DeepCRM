	@Override
	protected void populateActivationSpecProperties(BeanWrapper bw, JmsActivationSpecConfig config) {
		super.populateActivationSpecProperties(bw, config);
		if (config.getMaxConcurrency() > 0) {
			if (bw.isWritableProperty("maxSessions")) {
				// ActiveMQ
				bw.setPropertyValue("maxSessions", Integer.toString(config.getMaxConcurrency()));
			}
			else if (bw.isWritableProperty("maxNumberOfWorks")) {
				// JORAM
				bw.setPropertyValue("maxNumberOfWorks", Integer.toString(config.getMaxConcurrency()));
			}
			else if (bw.isWritableProperty("maxConcurrency")){
				// WebSphere
				bw.setPropertyValue("maxConcurrency", Integer.toString(config.getMaxConcurrency()));
			}
		}
		if (config.getPrefetchSize() > 0) {
			if (bw.isWritableProperty("maxMessagesPerSessions")) {
				// ActiveMQ
				bw.setPropertyValue("maxMessagesPerSessions", Integer.toString(config.getPrefetchSize()));
			}
			else if (bw.isWritableProperty("maxMessages")) {
				// JORAM
				bw.setPropertyValue("maxMessages", Integer.toString(config.getPrefetchSize()));
			}
			else if (bw.isWritableProperty("maxBatchSize")){
				// WebSphere
				bw.setPropertyValue("maxBatchSize", Integer.toString(config.getPrefetchSize()));
			}
		}
	}
