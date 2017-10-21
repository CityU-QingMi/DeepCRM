	private void assertTypesAreResolvable() {
		for ( XProperty xProperty : persistentAttributeMap.values() ) {
			if ( !xProperty.isTypeResolved() && !discoverTypeWithoutReflection( xProperty ) ) {
				String msg = "Property " + StringHelper.qualify( xClass.getName(), xProperty.getName() ) +
						" has an unbound type and no explicit target entity. Resolve this Generic usage issue" +
						" or set an explicit target attribute (eg @OneToMany(target=) or use an explicit @Type";
				throw new AnnotationException( msg );
			}
		}
	}
