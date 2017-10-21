	private Attributes getAttributes(StartElement event) {
		AttributesImpl attributes = new AttributesImpl();
		for (Iterator i = event.getAttributes(); i.hasNext();) {
			Attribute attribute = (Attribute) i.next();
			QName qName = attribute.getName();
			String namespace = qName.getNamespaceURI();
			if (namespace == null || !hasNamespacesFeature()) {
				namespace = "";
			}
			String type = attribute.getDTDType();
			if (type == null) {
				type = "CDATA";
			}
			attributes.addAttribute(namespace, qName.getLocalPart(), toQualifiedName(qName), type, attribute.getValue());
		}
		if (hasNamespacePrefixesFeature()) {
			for (Iterator i = event.getNamespaces(); i.hasNext();) {
				Namespace namespace = (Namespace) i.next();
				String prefix = namespace.getPrefix();
				String namespaceUri = namespace.getNamespaceURI();
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
