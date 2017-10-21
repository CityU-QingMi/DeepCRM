	private void parseMappedSuperClass(
			Collection<org.hibernate.jpamodelgen.xml.jaxb.MappedSuperclass> mappedSuperClasses,
			String defaultPackageName) {
		for ( org.hibernate.jpamodelgen.xml.jaxb.MappedSuperclass mappedSuperClass : mappedSuperClasses ) {
			String fqcn = StringUtil.determineFullyQualifiedClassName(
					defaultPackageName, mappedSuperClass.getClazz()
			);
			// we have to extract the package name from the fqcn. Maybe the entity was setting a fqcn directly
			String pkg = StringUtil.packageNameFromFqcn( fqcn );

			if ( !xmlMappedTypeExists( fqcn ) ) {
				context.logMessage(
						Diagnostic.Kind.WARNING,
						fqcn + " is mapped in xml, but class does not exist. Skipping meta model generation."
				);
				continue;
			}

			XmlMetaEntity metaEntity = new XmlMetaEntity(
					mappedSuperClass, pkg, getXmlMappedType( fqcn ), context
			);

			if ( context.containsMetaEntity( fqcn ) ) {
				context.logMessage(
						Diagnostic.Kind.WARNING,
						fqcn + " was already processed once. Skipping second occurrence."
				);
			}
			context.addMetaEntity( fqcn, metaEntity );
		}
	}
