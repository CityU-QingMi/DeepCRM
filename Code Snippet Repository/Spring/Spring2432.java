	public void prepare() {
		if (this.server == null) {
			this.server = this.connector.connect(this.serviceUrl, this.environment, this.agentId);
		}
		try {
			this.actualObjectNames = getResolvedObjectNames();
			if (this.actualObjectNames != null) {
				if (logger.isDebugEnabled()) {
					logger.debug("Registering NotificationListener for MBeans " + Arrays.asList(this.actualObjectNames));
				}
				for (ObjectName actualObjectName : this.actualObjectNames) {
					this.server.addNotificationListener(
							actualObjectName, getNotificationListener(), getNotificationFilter(), getHandback());
				}
			}
		}
		catch (IOException ex) {
			throw new MBeanServerNotFoundException(
					"Could not connect to remote MBeanServer at URL [" + this.serviceUrl + "]", ex);
		}
		catch (Exception ex) {
			throw new JmxException("Unable to register NotificationListener", ex);
		}
	}
