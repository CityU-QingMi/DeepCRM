		private String getAliasedAttributeName(AliasFor aliasFor, Method attribute) {
			String attributeName = aliasFor.attribute();
			String value = aliasFor.value();
			boolean attributeDeclared = StringUtils.hasText(attributeName);
			boolean valueDeclared = StringUtils.hasText(value);

			// Ensure user did not declare both 'value' and 'attribute' in @AliasFor
			if (attributeDeclared && valueDeclared) {
				String msg = String.format("In @AliasFor declared on attribute '%s' in annotation [%s], attribute 'attribute' " +
						"and its alias 'value' are present with values of [%s] and [%s], but only one is permitted.",
						attribute.getName(), attribute.getDeclaringClass().getName(), attributeName, value);
				throw new AnnotationConfigurationException(msg);
			}

			// Either explicit attribute name or pointing to same-named attribute by default
			attributeName = (attributeDeclared ? attributeName : value);
			return (StringUtils.hasText(attributeName) ? attributeName.trim() : attribute.getName());
		}
