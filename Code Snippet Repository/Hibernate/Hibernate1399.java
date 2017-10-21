	private Property bind(Property prop) {
		if (isId) {
			final RootClass rootClass = ( RootClass ) holder.getPersistentClass();
			//if an xToMany, it as to be wrapped today.
			//FIXME this pose a problem as the PK is the class instead of the associated class which is not really compliant with the spec
			if ( isXToMany || entityBinder.wrapIdsInEmbeddedComponents() ) {
				Component identifier = (Component) rootClass.getIdentifier();
				if (identifier == null) {
					identifier = AnnotationBinder.createComponent(
							holder,
							new PropertyPreloadedData(null, null, null),
							true,
							false,
							buildingContext
					);
					rootClass.setIdentifier( identifier );
					identifier.setNullValue( "undefined" );
					rootClass.setEmbeddedIdentifier( true );
					rootClass.setIdentifierMapper( identifier );
				}
				//FIXME is it good enough?
				identifier.addProperty( prop );
			}
			else {
				rootClass.setIdentifier( ( KeyValue ) getValue() );
				if (embedded) {
					rootClass.setEmbeddedIdentifier( true );
				}
				else {
					rootClass.setIdentifierProperty( prop );
					final org.hibernate.mapping.MappedSuperclass superclass = BinderHelper.getMappedSuperclassOrNull(
							declaringClass,
							inheritanceStatePerClass,
							buildingContext
					);
					if (superclass != null) {
						superclass.setDeclaredIdentifierProperty(prop);
					}
					else {
						//we know the property is on the actual entity
						rootClass.setDeclaredIdentifierProperty( prop );
					}
				}
			}
		}
		else {
			holder.addProperty( prop, columns, declaringClass );
		}
		return prop;
	}
