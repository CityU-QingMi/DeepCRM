		@Override
		public Member resolveMember(AttributeContext attributeContext) {
			final EmbeddableTypeImpl embeddableType = (EmbeddableTypeImpl<?>) attributeContext.getOwnerType();
			final String attributeName = attributeContext.getPropertyMapping().getName();

			final Getter getter = embeddableType.getHibernateType()
					.getComponentTuplizer()
					.getGetter( embeddableType.getHibernateType().getPropertyIndex( attributeName ) );
			return PropertyAccessMapImpl.GetterImpl.class.isInstance( getter )
					? new MapMember( attributeName, attributeContext.getPropertyMapping().getType().getReturnedClass() )
					: getter.getMember();
		}
