	private void parseEntities(Collection<Entity> entities, String defaultPackageName) {
		for ( Entity entity : entities ) {
			String fqcn = StringUtil.determineFullyQualifiedClassName( defaultPackageName, entity.getClazz() );

			if ( !xmlMappedTypeExists( fqcn ) ) {
				context.logMessage(
						Diagnostic.Kind.WARNING,
						fqcn + " is mapped in xml, but class does not exist. Skipping meta model generation."
				);
				continue;
			}

			XmlMetaEntity metaEntity = new XmlMetaEntity(
					entity, defaultPackageName, getXmlMappedType( fqcn ), context
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
