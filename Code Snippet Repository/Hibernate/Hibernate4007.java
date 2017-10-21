		@Override
		public Member resolveMember(AttributeContext attributeContext) {
			final AbstractManagedType ownerType = attributeContext.getOwnerType();
			final Property property = attributeContext.getPropertyMapping();
			final Type.PersistenceType persistenceType = ownerType.getPersistenceType();
			if ( Type.PersistenceType.EMBEDDABLE == persistenceType ) {
				return embeddedMemberResolver.resolveMember( attributeContext );
			}
			else if ( Type.PersistenceType.ENTITY == persistenceType
					|| Type.PersistenceType.MAPPED_SUPERCLASS == persistenceType ) {
				final AbstractIdentifiableType identifiableType = (AbstractIdentifiableType) ownerType;
				final EntityMetamodel entityMetamodel = getDeclarerEntityMetamodel( identifiableType );
				final String propertyName = property.getName();
				final Integer index = entityMetamodel.getPropertyIndexOrNull( propertyName );
				if ( index == null ) {
					// just like in #determineIdentifierJavaMember , this *should* indicate we have an IdClass mapping
					return virtualIdentifierMemberResolver.resolveMember( attributeContext );
				}
				else {
					final Getter getter = entityMetamodel.getTuplizer().getGetter( index );
					return PropertyAccessMapImpl.GetterImpl.class.isInstance( getter )
							? new MapMember( propertyName, property.getType().getReturnedClass() )
							: getter.getMember();
				}
			}
			else {
				throw new IllegalArgumentException( "Unexpected owner type : " + persistenceType );
			}
		}
