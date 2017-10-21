	@Override
	@Nullable
	public final Object getAttributeValue(Method attributeMethod) {
		String attributeName = attributeMethod.getName();
		Object attributeValue = getRawAttributeValue(attributeMethod);

		List<String> aliasNames = this.attributeAliasMap.get(attributeName);
		if (aliasNames != null) {
			Object defaultValue = AnnotationUtils.getDefaultValue(this.annotationType, attributeName);
			for (String aliasName : aliasNames) {
				Object aliasValue = getRawAttributeValue(aliasName);

				if (!ObjectUtils.nullSafeEquals(attributeValue, aliasValue) &&
						!ObjectUtils.nullSafeEquals(attributeValue, defaultValue) &&
						!ObjectUtils.nullSafeEquals(aliasValue, defaultValue)) {
					String elementName = (this.annotatedElement != null ? this.annotatedElement.toString() : "unknown element");
					throw new AnnotationConfigurationException(String.format(
							"In annotation [%s] declared on %s and synthesized from [%s], attribute '%s' and its " +
							"alias '%s' are present with values of [%s] and [%s], but only one is permitted.",
							this.annotationType.getName(), elementName, this.source, attributeName, aliasName,
							ObjectUtils.nullSafeToString(attributeValue), ObjectUtils.nullSafeToString(aliasValue)));
				}

				// If the user didn't declare the annotation with an explicit value,
				// use the value of the alias instead.
				if (ObjectUtils.nullSafeEquals(attributeValue, defaultValue)) {
					attributeValue = aliasValue;
				}
			}
		}

		return attributeValue;
	}
