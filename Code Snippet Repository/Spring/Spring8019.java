	private JAXBContext createJaxbContextFromClasses(Class<?>... classesToBeBound) throws JAXBException {
		if (logger.isInfoEnabled()) {
			logger.info("Creating JAXBContext with classes to be bound [" +
					StringUtils.arrayToCommaDelimitedString(classesToBeBound) + "]");
		}
		if (this.jaxbContextProperties != null) {
			return JAXBContext.newInstance(classesToBeBound, this.jaxbContextProperties);
		}
		else {
			return JAXBContext.newInstance(classesToBeBound);
		}
	}
