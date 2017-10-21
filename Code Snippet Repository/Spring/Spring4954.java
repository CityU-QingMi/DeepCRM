	private Attributes getAttributes() {
		AttributesImpl attributes = new AttributesImpl();
		for (int i = 0; i < this.reader.getAttributeCount(); i++) {
			String namespace = this.reader.getAttributeNamespace(i);
			if (namespace == null || !hasNamespacesFeature()) {
				namespace = "";
			}
			String type = this.reader.getAttributeType(i);
			if (type == null) {
				type = "CDATA";
			}
			attributes.addAttribute(namespace, this.reader.getAttributeLocalName(i),
					toQualifiedName(this.reader.getAttributeName(i)), type, this.reader.getAttributeValue(i));
		}
		if (hasNamespacePrefixesFeature()) {
			for (int i = 0; i < this.reader.getNamespaceCount(); i++) {
				String prefix = this.reader.getNamespacePrefix(i);
				String namespaceUri = this.reader.getNamespaceURI(i);
				String qName;
				if (StringUtils.hasLength(prefix)) {
					qName = "xmlns:" + prefix;
				}
				else {
					qName = "xmlns";
				}
				attributes.addAttribute("", "", qName, "CDATA", namespaceUri);
			}
		}

		return attributes;
	}
