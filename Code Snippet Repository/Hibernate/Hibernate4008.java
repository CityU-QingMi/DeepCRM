		@Override
		public Member resolveMember(AttributeContext attributeContext) {
			final AbstractIdentifiableType identifiableType = (AbstractIdentifiableType) attributeContext.getOwnerType();
			final EntityMetamodel entityMetamodel = getDeclarerEntityMetamodel( identifiableType );
			if ( !attributeContext.getPropertyMapping().getName()
					.equals( entityMetamodel.getIdentifierProperty().getName() ) ) {
				// this *should* indicate processing part of an IdClass...
				return virtualIdentifierMemberResolver.resolveMember( attributeContext );
			}
			final Getter getter = entityMetamodel.getTuplizer().getIdentifierGetter();
			if ( PropertyAccessMapImpl.GetterImpl.class.isInstance( getter ) ) {
				return new MapMember(
						entityMetamodel.getIdentifierProperty().getName(),
						entityMetamodel.getIdentifierProperty().getType().getReturnedClass()
				);
			}
			else {
				return getter.getMember();
			}
		}
