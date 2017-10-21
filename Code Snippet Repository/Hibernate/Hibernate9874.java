	private String getMappedBy(PersistentClass referencedClass, ValueHolder valueHolder) {
		// If there's an @AuditMappedBy specified, returning it directly.
		final String auditMappedBy = propertyAuditingData.getAuditMappedBy();
		if ( auditMappedBy != null ) {
			return auditMappedBy;
		}

		// searching in referenced class
		String mappedBy = this.searchMappedBy( referencedClass, valueHolder );

		if ( mappedBy == null ) {
			LOG.debugf(
					"Going to search the mapped by attribute for %s in superclasses of entity: %s",
					propertyName,
					referencedClass.getClassName()
			);

			PersistentClass tempClass = referencedClass;
			while ( mappedBy == null && tempClass.getSuperclass() != null ) {
				LOG.debugf( "Searching in superclass: %s", tempClass.getSuperclass().getClassName() );
				mappedBy = this.searchMappedBy( tempClass.getSuperclass(), valueHolder );
				tempClass = tempClass.getSuperclass();
			}
		}

		if ( mappedBy == null ) {
			throw new MappingException(
					"Unable to read the mapped by attribute for " + propertyName + " in "
							+ referencedClass.getClassName() + "!"
			);
		}

		return mappedBy;
	}
