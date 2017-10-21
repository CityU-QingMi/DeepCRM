	private JAXBContext createJaxbContextFromContextPath(String contextPath) throws JAXBException {
		if (logger.isInfoEnabled()) {
			logger.info("Creating JAXBContext with context path [" + this.contextPath + "]");
		}
		if (this.jaxbContextProperties != null) {
			if (this.beanClassLoader != null) {
				return JAXBContext.newInstance(contextPath, this.beanClassLoader, this.jaxbContextProperties);
			}
			else {
				// analogous to the JAXBContext.newInstance(String) implementation
				return JAXBContext.newInstance(contextPath, Thread.currentThread().getContextClassLoader(),
						this.jaxbContextProperties);
			}
		}
		else {
			if (this.beanClassLoader != null) {
				return JAXBContext.newInstance(contextPath, this.beanClassLoader);
			}
			else {
				return JAXBContext.newInstance(contextPath);
			}
		}
	}
