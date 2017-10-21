	private static Map<String, ForeignKey> buildForeignKeyOverride(XAnnotatedElement element, String path) {
		Map<String, ForeignKey> foreignKeyOverride = new HashMap<String, ForeignKey>();
		if ( element != null ) {
			AssociationOverride[] overrides = buildAssociationOverrides( element, path );
			if ( overrides != null ) {
				for ( AssociationOverride depAttr : overrides ) {
					foreignKeyOverride.put( StringHelper.qualify( path, depAttr.name() ), depAttr.foreignKey() );
				}
			}
		}
		return foreignKeyOverride;
	}
