	private List<Attribute> getAttributes(Attributes attributes) {
		List<Attribute> result = new ArrayList<>();
		for (int i = 0; i < attributes.getLength(); i++) {
			QName attrName = toQName(attributes.getURI(i), attributes.getQName(i));
			if (!isNamespaceDeclaration(attrName)) {
				result.add(this.eventFactory.createAttribute(attrName, attributes.getValue(i)));
			}
		}
		return result;
	}
