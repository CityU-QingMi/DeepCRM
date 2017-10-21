	public void prepare() {
		synchronized (this.preparationMonitor) {
			if (this.server != null) {
				this.serverToUse = this.server;
			}
			else {
				this.serverToUse = null;
				this.serverToUse = this.connector.connect(this.serviceUrl, this.environment, this.agentId);
			}
			this.invocationHandler = null;
			if (this.useStrictCasing) {
				Assert.state(this.objectName != null, "No ObjectName set");
				// Use the JDK's own MBeanServerInvocationHandler, in particular for native MXBean support.
				this.invocationHandler = new MBeanServerInvocationHandler(this.serverToUse, this.objectName,
						(this.managementInterface != null && JMX.isMXBeanInterface(this.managementInterface)));
			}
			else {
				// Non-strict casing can only be achieved through custom invocation handling.
				// Only partial MXBean support available!
				retrieveMBeanInfo(this.serverToUse);
			}
		}
	}
