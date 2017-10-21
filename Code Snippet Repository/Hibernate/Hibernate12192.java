	private void determineAnnotationAccessTypes() {
		for ( EntityMappings mappings : entityMappings ) {
			String fqcn;
			String packageName = mappings.getPackage();

			for ( Entity entity : mappings.getEntity() ) {
				String name = entity.getClazz();
				fqcn = StringUtil.determineFullyQualifiedClassName( packageName, name );
				TypeElement element = context.getTypeElementForFullyQualifiedName( fqcn );
				if ( element != null ) {
					TypeUtils.determineAccessTypeForHierarchy( element, context );
				}
			}

			for ( org.hibernate.jpamodelgen.xml.jaxb.MappedSuperclass mappedSuperClass : mappings.getMappedSuperclass() ) {
				String name = mappedSuperClass.getClazz();
				fqcn = StringUtil.determineFullyQualifiedClassName( packageName, name );
				TypeElement element = context.getTypeElementForFullyQualifiedName( fqcn );
				if ( element != null ) {
					TypeUtils.determineAccessTypeForHierarchy( element, context );
				}
			}
		}
	}
