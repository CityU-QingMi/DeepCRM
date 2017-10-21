		@Override
		public Member resolveMember(AttributeContext attributeContext) {
			final AbstractIdentifiableType identifiableType = (AbstractIdentifiableType) attributeContext.getOwnerType();
			final EntityMetamodel entityMetamodel = getDeclarerEntityMetamodel( identifiableType );
			final String versionPropertyName = attributeContext.getPropertyMapping().getName();
			if ( !versionPropertyName.equals( entityMetamodel.getVersionProperty().getName() ) ) {
				// this should never happen, but to be safe...
				throw new IllegalArgumentException( "Given property did not match declared version property" );
			}

			final Getter getter = entityMetamodel.getTuplizer().getVersionGetter();
			if ( PropertyAccessMapImpl.GetterImpl.class.isInstance( getter ) ) {
				return new MapMember(
						versionPropertyName,
						attributeContext.getPropertyMapping().getType().getReturnedClass()
				);
			}
			else {
				return getter.getMember();
			}
		}
