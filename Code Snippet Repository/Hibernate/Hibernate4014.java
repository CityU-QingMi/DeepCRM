	@SuppressWarnings("")
	private <Y> Type<Y> getMetaModelType(ValueContext typeContext) {
		switch ( typeContext.getValueClassification() ) {
			case BASIC: {
				return new BasicTypeImpl<Y>(
						typeContext.getBindableType(),
						Type.PersistenceType.BASIC
				);
			}
			case ENTITY: {
				final org.hibernate.type.EntityType type = (EntityType) typeContext.getValue().getType();
				return (Type<Y>) context.locateEntityType( type.getAssociatedEntityName() );
			}
			case EMBEDDABLE: {
				final Component component = (Component) typeContext.getValue();
				Class javaType;
				if ( component.getComponentClassName() == null ) {
					javaType = typeContext.getBindableType();
				}
				else {
					javaType = component.getComponentClass();
				}
				final EmbeddableTypeImpl<Y> embeddableType = new EmbeddableTypeImpl<Y>(
						javaType,
						typeContext.getAttributeMetadata().getOwnerType(),
						(ComponentType) typeContext.getValue().getType()
				);
				context.registerEmbeddedableType( embeddableType );
				final Iterator<Property> subProperties = component.getPropertyIterator();
				while ( subProperties.hasNext() ) {
					final Property property = subProperties.next();
					final AttributeImplementor<Y, Object> attribute = buildAttribute( embeddableType, property );
					if ( attribute != null ) {
						embeddableType.getBuilder().addAttribute( attribute );
					}
				}
				embeddableType.lock();
				return embeddableType;
			}
			default: {
				throw new AssertionFailure( "Unknown type : " + typeContext.getValueClassification() );
			}
		}
	}
