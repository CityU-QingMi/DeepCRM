	private void ensureProperMember(Set<?> attributes) {
		//we do not update the set so we are safe
		@SuppressWarnings( "unchecked" )
		final Set<Attribute<?, ?>> safeAttributes = ( Set<Attribute<?, ?>> ) attributes;
		for (Attribute<?,?> attribute : safeAttributes ) {
			final String name = attribute.getJavaMember().getName();
			assertNotNull( attribute.getJavaMember() );
			assertTrue( name.toLowerCase(Locale.ROOT).endsWith( attribute.getName().toLowerCase(Locale.ROOT) ) );
		}
	}
