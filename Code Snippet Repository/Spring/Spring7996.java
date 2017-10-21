	protected void customizeMarshaller(Marshaller marshaller) {
		marshaller.setValidation(this.validating);
		marshaller.setSuppressNamespaces(this.suppressNamespaces);
		marshaller.setSuppressXSIType(this.suppressXsiType);
		marshaller.setMarshalAsDocument(this.marshalAsDocument);
		marshaller.setMarshalExtendedType(this.marshalExtendedType);
		marshaller.setRootElement(this.rootElement);
		marshaller.setNoNamespaceSchemaLocation(this.noNamespaceSchemaLocation);
		marshaller.setSchemaLocation(this.schemaLocation);
		marshaller.setUseXSITypeAtRoot(this.useXSITypeAtRoot);
		if (this.doctypes != null) {
			this.doctypes.forEach(marshaller::setDoctype);
		}
		if (this.processingInstructions != null) {
			this.processingInstructions.forEach(marshaller::addProcessingInstruction);
		}
		if (this.namespaceMappings != null) {
			this.namespaceMappings.forEach(marshaller::setNamespaceMapping);
		}
	}
