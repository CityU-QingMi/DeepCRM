	private static Map<String, JoinColumn[]> buildJoinColumnOverride(XAnnotatedElement element, String path) {
		Map<String, JoinColumn[]> columnOverride = new HashMap<String, JoinColumn[]>();
		if ( element != null ) {
			AssociationOverride[] overrides = buildAssociationOverrides( element, path );
			if ( overrides != null ) {
				for ( AssociationOverride depAttr : overrides ) {
					columnOverride.put(
							StringHelper.qualify( path, depAttr.name() ),
							depAttr.joinColumns()
					);
				}
			}
		}
		return columnOverride;
	}
