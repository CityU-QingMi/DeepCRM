	public NamedEntityGraphDefinition(NamedEntityGraph annotation, String jpaEntityName, String entityName) {
		this.annotation = annotation;
		this.jpaEntityName = jpaEntityName;
		this.entityName = entityName;
		this.name = StringHelper.isNotEmpty( annotation.name() )
				? annotation.name()
				: jpaEntityName;
		if ( name == null ) {
			throw new IllegalArgumentException( "Named entity graph name cannot be null" );
		}
	}
