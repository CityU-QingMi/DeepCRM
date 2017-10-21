	public Builder<X> getBuilder() {
		final AbstractManagedType.Builder<X> managedBuilder = super.getBuilder();
		return new Builder<X>() {
			public void applyIdAttribute(SingularAttributeImpl<X, ?> idAttribute) {
				AbstractIdentifiableType.this.id = idAttribute;
				managedBuilder.addAttribute( idAttribute );
			}

			public void applyIdClassAttributes(Set<SingularAttribute<? super X,?>> idClassAttributes) {
				for ( SingularAttribute<? super X,?> idClassAttribute : idClassAttributes ) {
					if ( AbstractIdentifiableType.this == idClassAttribute.getDeclaringType() ) {
						@SuppressWarnings({ "unchecked" })
						SingularAttribute<X,?> declaredAttribute = ( SingularAttribute<X,?> ) idClassAttribute;
						addAttribute( declaredAttribute );
					}
				}
				AbstractIdentifiableType.this.idClassAttributes = idClassAttributes;
			}

			public void applyVersionAttribute(SingularAttributeImpl<X, ?> versionAttribute) {
				AbstractIdentifiableType.this.version = versionAttribute;
				managedBuilder.addAttribute( versionAttribute );
			}

			public void addAttribute(Attribute<X, ?> attribute) {
				managedBuilder.addAttribute( attribute );
			}
		};
	}
