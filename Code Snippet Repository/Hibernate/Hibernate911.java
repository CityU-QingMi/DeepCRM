	private void bindComponent(
			MappingDocument sourceDocument,
			EmbeddableSource embeddableSource,
			Component component,
			String containingClassName,
			String propertyName,
			String xmlNodeName,
			boolean isVirtual) {
		final String fullRole = embeddableSource.getAttributeRoleBase().getFullPath();
		final String explicitComponentClassName = extractExplicitComponentClassName( embeddableSource );

		bindComponent(
				sourceDocument,
				fullRole,
				embeddableSource,
				component,
				explicitComponentClassName,
				containingClassName,
				propertyName,
				isVirtual,
				embeddableSource.isDynamic(),
				xmlNodeName
		);
	}
