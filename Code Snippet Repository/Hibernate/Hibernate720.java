	@Override
	public SessionFactoryBuilder applyConnectionReleaseMode(ConnectionReleaseMode connectionReleaseMode) {
		if ( this.options.connectionHandlingMode == null ) {
			this.options.connectionHandlingMode = PhysicalConnectionHandlingMode.interpret(
					ConnectionAcquisitionMode.AS_NEEDED,
					connectionReleaseMode
			);
		}
		else {
			this.options.connectionHandlingMode = PhysicalConnectionHandlingMode.interpret(
					this.options.connectionHandlingMode.getAcquisitionMode(),
					connectionReleaseMode
			);
		}
		return this;
	}
