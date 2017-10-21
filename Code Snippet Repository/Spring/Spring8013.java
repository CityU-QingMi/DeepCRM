	public JAXBContext getJaxbContext() {
		JAXBContext context = this.jaxbContext;
		if (context != null) {
			return context;
		}
		synchronized (this.jaxbContextMonitor) {
			context = this.jaxbContext;
			if (context == null) {
				try {
					if (StringUtils.hasLength(this.contextPath)) {
						context = createJaxbContextFromContextPath(this.contextPath);
					}
					else if (!ObjectUtils.isEmpty(this.classesToBeBound)) {
						context = createJaxbContextFromClasses(this.classesToBeBound);
					}
					else if (!ObjectUtils.isEmpty(this.packagesToScan)) {
						context = createJaxbContextFromPackages(this.packagesToScan);
					}
					else {
						context = JAXBContext.newInstance();
					}
					this.jaxbContext = context;
				}
				catch (JAXBException ex) {
					throw convertJaxbException(ex);
				}
			}
			return context;
		}
	}
