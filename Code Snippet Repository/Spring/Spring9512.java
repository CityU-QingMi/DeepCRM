	public Service createJaxWsService() {
		Assert.notNull(this.serviceName, "No service name specified");
		Service service;

		if (this.serviceFeatures != null) {
			service = (this.wsdlDocumentUrl != null ?
				Service.create(this.wsdlDocumentUrl, getQName(this.serviceName), this.serviceFeatures) :
				Service.create(getQName(this.serviceName), this.serviceFeatures));
		}
		else {
			service = (this.wsdlDocumentUrl != null ?
					Service.create(this.wsdlDocumentUrl, getQName(this.serviceName)) :
					Service.create(getQName(this.serviceName)));
		}

		if (this.executor != null) {
			service.setExecutor(this.executor);
		}
		if (this.handlerResolver != null) {
			service.setHandlerResolver(this.handlerResolver);
		}

		return service;
	}
