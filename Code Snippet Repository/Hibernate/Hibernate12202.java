	private void parseEmbeddable(
			Collection<org.hibernate.jpamodelgen.xml.jaxb.Embeddable> embeddables,
			String defaultPackageName) {
		for ( org.hibernate.jpamodelgen.xml.jaxb.Embeddable embeddable : embeddables ) {
			String fqcn = StringUtil.determineFullyQualifiedClassName( defaultPackageName, embeddable.getClazz() );
			// we have to extract the package name from the fqcn. Maybe the entity was setting a fqcn directly
			String pkg = StringUtil.packageNameFromFqcn( fqcn );

			if ( !xmlMappedTypeExists( fqcn ) ) {
				context.logMessage(
						Diagnostic.Kind.WARNING,
						fqcn + " is mapped in xml, but class does not exist. Skipping meta model generation."
				);
				continue;
			}

			XmlMetaEntity metaEntity = new XmlMetaEntity( embeddable, pkg, getXmlMappedType( fqcn ), context );
			if ( context.containsMetaEmbeddable( fqcn ) ) {
				context.logMessage(
						Diagnostic.Kind.WARNING,
						fqcn + " was already processed once. Skipping second occurrence."
				);
			}
			context.addMetaEmbeddable( fqcn, metaEntity );
		}
	}
