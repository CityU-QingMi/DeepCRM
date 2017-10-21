		@Override
		public Member resolveMember(AttributeContext attributeContext) {
			final AbstractIdentifiableType identifiableType = (AbstractIdentifiableType) attributeContext.getOwnerType();
			final EntityMetamodel entityMetamodel = getDeclarerEntityMetamodel( identifiableType );
			if ( !entityMetamodel.getIdentifierProperty().isVirtual() ) {
				throw new IllegalArgumentException( "expecting IdClass mapping" );
			}
			org.hibernate.type.Type type = entityMetamodel.getIdentifierProperty().getType();
			if ( !EmbeddedComponentType.class.isInstance( type ) ) {
				throw new IllegalArgumentException( "expecting IdClass mapping" );
			}

			final EmbeddedComponentType componentType = (EmbeddedComponentType) type;
			final String attributeName = attributeContext.getPropertyMapping().getName();

			final Getter getter = componentType.getComponentTuplizer()
					.getGetter( componentType.getPropertyIndex( attributeName ) );

			return PropertyAccessMapImpl.GetterImpl.class.isInstance( getter )
					? new MapMember( attributeName, attributeContext.getPropertyMapping().getType().getReturnedClass() )
					: getter.getMember();
		}
