	protected Object getPortStub(Service service, @Nullable QName portQName) {
		if (this.portFeatures != null) {
			return (portQName != null ? service.getPort(portQName, getServiceInterface(), this.portFeatures) :
					service.getPort(getServiceInterface(), this.portFeatures));
		}
		else {
			return (portQName != null ? service.getPort(portQName, getServiceInterface()) :
					service.getPort(getServiceInterface()));
		}
	}
