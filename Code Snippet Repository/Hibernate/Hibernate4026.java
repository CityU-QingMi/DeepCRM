	private <X> void applyIdMetadata(PersistentClass persistentClass, EntityTypeImpl<X> jpaEntityType) {
		if ( persistentClass.hasIdentifierProperty() ) {
			final Property declaredIdentifierProperty = persistentClass.getDeclaredIdentifierProperty();
			if ( declaredIdentifierProperty != null ) {
				jpaEntityType.getBuilder().applyIdAttribute(
						attributeFactory.buildIdAttribute( jpaEntityType, declaredIdentifierProperty )
				);
			}
		}
		else if ( persistentClass.hasIdentifierMapper() ) {
			@SuppressWarnings("unchecked")
			Iterator<Property> propertyIterator = persistentClass.getIdentifierMapper().getPropertyIterator();
			Set<SingularAttribute<? super X, ?>> attributes = buildIdClassAttributes( jpaEntityType, propertyIterator );
			jpaEntityType.getBuilder().applyIdClassAttributes( attributes );
		}
		else {
			final KeyValue value = persistentClass.getIdentifier();
			if ( value instanceof Component ) {
				final Component component = (Component) value;
				if ( component.getPropertySpan() > 1 ) {
					//FIXME we are an Hibernate embedded id (ie not type)
				}
				else {
					//FIXME take care of declared vs non declared property
					jpaEntityType.getBuilder().applyIdAttribute(
							attributeFactory.buildIdAttribute(
									jpaEntityType,
									(Property) component.getPropertyIterator().next()
							)
					);
				}
			}
		}
	}
