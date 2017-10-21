		@Override
		public void postProcess(@Nullable AnnotatedElement element, Annotation annotation, AnnotationAttributes attributes) {
			annotation = AnnotationUtils.synthesizeAnnotation(annotation, element);
			Class<? extends Annotation> targetAnnotationType = attributes.annotationType();

			// Track which attribute values have already been replaced so that we can short
			// circuit the search algorithms.
			Set<String> valuesAlreadyReplaced = new HashSet<>();

			for (Method attributeMethod : AnnotationUtils.getAttributeMethods(annotation.annotationType())) {
				String attributeName = attributeMethod.getName();
				String attributeOverrideName = AnnotationUtils.getAttributeOverrideName(attributeMethod, targetAnnotationType);

				// Explicit annotation attribute override declared via @AliasFor
				if (attributeOverrideName != null) {
					if (valuesAlreadyReplaced.contains(attributeOverrideName)) {
						continue;
					}

					List<String> targetAttributeNames = new ArrayList<>();
					targetAttributeNames.add(attributeOverrideName);
					valuesAlreadyReplaced.add(attributeOverrideName);

					// Ensure all aliased attributes in the target annotation are overridden. (SPR-14069)
					List<String> aliases = AnnotationUtils.getAttributeAliasMap(targetAnnotationType).get(attributeOverrideName);
					if (aliases != null) {
						for (String alias : aliases) {
							if (!valuesAlreadyReplaced.contains(alias)) {
								targetAttributeNames.add(alias);
								valuesAlreadyReplaced.add(alias);
							}
						}
					}

					overrideAttributes(element, annotation, attributes, attributeName, targetAttributeNames);
				}
				// Implicit annotation attribute override based on convention
				else if (!AnnotationUtils.VALUE.equals(attributeName) && attributes.containsKey(attributeName)) {
					overrideAttribute(element, annotation, attributes, attributeName, attributeName);
				}
			}
		}
