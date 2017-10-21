	public Builder<X> getBuilder() {
		if ( locked ) {
			throw new IllegalStateException( "Type has been locked" );
		}
		return new Builder<X>() {
			@Override
			@SuppressWarnings("unchecked")
			public void addAttribute(Attribute<X,?> attribute) {
				declaredAttributes.put( attribute.getName(), attribute );
				final Bindable.BindableType bindableType = ( ( Bindable ) attribute ).getBindableType();
				switch ( bindableType ) {
					case SINGULAR_ATTRIBUTE : {
						declaredSingularAttributes.put( attribute.getName(), (SingularAttribute<X,?>) attribute );
						break;
					}
					case PLURAL_ATTRIBUTE : {
						declaredPluralAttributes.put(attribute.getName(), (PluralAttribute<X,?,?>) attribute );
						break;
					}
					default : {
						throw new AssertionFailure( "unknown bindable type: " + bindableType );
					}
				}
			}
		};
	}
