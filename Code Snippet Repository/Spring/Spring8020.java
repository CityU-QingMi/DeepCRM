	private JAXBContext createJaxbContextFromPackages(String... packagesToScan) throws JAXBException {
		if (logger.isInfoEnabled()) {
			logger.info("Creating JAXBContext by scanning packages [" +
					StringUtils.arrayToCommaDelimitedString(packagesToScan) + "]");
		}
		ClassPathJaxb2TypeScanner scanner = new ClassPathJaxb2TypeScanner(this.beanClassLoader, packagesToScan);
		Class<?>[] jaxb2Classes = scanner.scanPackages();
		if (logger.isDebugEnabled()) {
			logger.debug("Found JAXB2 classes: [" + StringUtils.arrayToCommaDelimitedString(jaxb2Classes) + "]");
		}
		this.classesToBeBound = jaxb2Classes;
		if (this.jaxbContextProperties != null) {
			return JAXBContext.newInstance(jaxb2Classes, this.jaxbContextProperties);
		}
		else {
			return JAXBContext.newInstance(jaxb2Classes);
		}
	}
