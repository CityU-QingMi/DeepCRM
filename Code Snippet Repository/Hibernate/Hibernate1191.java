	private static Map<String, JoinTable> buildJoinTableOverride(XAnnotatedElement element, String path) {
		Map<String, JoinTable> tableOverride = new HashMap<String, JoinTable>();
		if ( element != null ) {
			AssociationOverride[] overrides = buildAssociationOverrides( element, path );
			if ( overrides != null ) {
				for ( AssociationOverride depAttr : overrides ) {
					if ( depAttr.joinColumns().length == 0 ) {
						tableOverride.put(
								StringHelper.qualify( path, depAttr.name() ),
								depAttr.joinTable()
						);
					}
				}
			}
		}
		return tableOverride;
	}
