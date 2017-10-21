	protected void customizeUnmarshaller(Unmarshaller unmarshaller) {
		unmarshaller.setValidation(this.validating);
		unmarshaller.setWhitespacePreserve(this.whitespacePreserve);
		unmarshaller.setIgnoreExtraAttributes(this.ignoreExtraAttributes);
		unmarshaller.setIgnoreExtraElements(this.ignoreExtraElements);
		unmarshaller.setObject(this.rootObject);
		unmarshaller.setReuseObjects(this.reuseObjects);
		unmarshaller.setClearCollections(this.clearCollections);
		if (this.namespaceToPackageMapping != null) {
			this.namespaceToPackageMapping.forEach(unmarshaller::addNamespaceToPackageMapping);
		}
		if (this.entityResolver != null) {
			unmarshaller.setEntityResolver(this.entityResolver);
		}
		if (this.classDescriptorResolver != null) {
			unmarshaller.setResolver(this.classDescriptorResolver);
		}
		if (this.idResolver != null) {
			unmarshaller.setIDResolver(this.idResolver);
		}
		if (this.objectFactory != null) {
			unmarshaller.setObjectFactory(this.objectFactory);
		}
		if (this.beanClassLoader != null) {
			unmarshaller.setClassLoader(this.beanClassLoader);
		}
	}
