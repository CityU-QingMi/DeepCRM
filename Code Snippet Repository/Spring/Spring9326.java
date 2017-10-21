	QName toQName(Class<?> outputClass) {
		String localPart;
		String namespaceUri;

		if (outputClass.isAnnotationPresent(XmlRootElement.class)) {
			XmlRootElement annotation = outputClass.getAnnotation(XmlRootElement.class);
			localPart = annotation.name();
			namespaceUri = annotation.namespace();
		}
		else if (outputClass.isAnnotationPresent(XmlType.class)) {
			XmlType annotation = outputClass.getAnnotation(XmlType.class);
			localPart = annotation.name();
			namespaceUri = annotation.namespace();
		}
		else {
			throw new IllegalArgumentException("Output class [" + outputClass.getName() +
					"] is neither annotated with @XmlRootElement nor @XmlType");
		}

		if (JAXB_DEFAULT_ANNOTATION_VALUE.equals(localPart)) {
			localPart = ClassUtils.getShortNameAsProperty(outputClass);
		}
		if (JAXB_DEFAULT_ANNOTATION_VALUE.equals(namespaceUri)) {
			Package outputClassPackage = outputClass.getPackage();
			if (outputClassPackage != null && outputClassPackage.isAnnotationPresent(XmlSchema.class)) {
				XmlSchema annotation = outputClassPackage.getAnnotation(XmlSchema.class);
				namespaceUri = annotation.namespace();
			}
			else {
				namespaceUri = XMLConstants.NULL_NS_URI;
			}
		}
		return new QName(namespaceUri, localPart);
	}
