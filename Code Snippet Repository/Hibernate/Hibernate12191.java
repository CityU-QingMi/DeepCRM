	private void determineXmlAccessTypes() {
		for ( EntityMappings mappings : entityMappings ) {
			String fqcn;
			String packageName = mappings.getPackage();
			AccessType defaultAccessType = determineEntityAccessType( mappings );

			for ( Entity entity : mappings.getEntity() ) {
				String name = entity.getClazz();
				fqcn = StringUtil.determineFullyQualifiedClassName( packageName, name );
				AccessType explicitAccessType = null;
				org.hibernate.jpamodelgen.xml.jaxb.AccessType type = entity.getAccess();
				if ( type != null ) {
					explicitAccessType = mapXmlAccessTypeToJpaAccessType( type );
				}
				AccessTypeInformation accessInfo = new AccessTypeInformation(
						fqcn, explicitAccessType, defaultAccessType
				);
				context.addAccessTypeInformation( fqcn, accessInfo );
			}

			for ( org.hibernate.jpamodelgen.xml.jaxb.MappedSuperclass mappedSuperClass : mappings.getMappedSuperclass() ) {
				String name = mappedSuperClass.getClazz();
				fqcn = StringUtil.determineFullyQualifiedClassName( packageName, name );
				AccessType explicitAccessType = null;
				org.hibernate.jpamodelgen.xml.jaxb.AccessType type = mappedSuperClass.getAccess();
				if ( type != null ) {
					explicitAccessType = mapXmlAccessTypeToJpaAccessType( type );
				}
				AccessTypeInformation accessInfo = new AccessTypeInformation(
						fqcn, explicitAccessType, defaultAccessType
				);
				context.addAccessTypeInformation( fqcn, accessInfo );
			}

			for ( org.hibernate.jpamodelgen.xml.jaxb.Embeddable embeddable : mappings.getEmbeddable() ) {
				String name = embeddable.getClazz();
				fqcn = StringUtil.determineFullyQualifiedClassName( packageName, name );
				AccessType explicitAccessType = null;
				org.hibernate.jpamodelgen.xml.jaxb.AccessType type = embeddable.getAccess();
				if ( type != null ) {
					explicitAccessType = mapXmlAccessTypeToJpaAccessType( type );
				}
				AccessTypeInformation accessInfo = new AccessTypeInformation(
						fqcn, explicitAccessType, defaultAccessType
				);
				context.addAccessTypeInformation( fqcn, accessInfo );
			}
		}
	}
