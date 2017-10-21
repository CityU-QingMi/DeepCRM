	private static Map<String, Column[]> buildColumnOverride(XAnnotatedElement element, String path) {
		Map<String, Column[]> columnOverride = new HashMap<String, Column[]>();
		if ( element != null ) {
			AttributeOverride singleOverride = element.getAnnotation( AttributeOverride.class );
			AttributeOverrides multipleOverrides = element.getAnnotation( AttributeOverrides.class );
			AttributeOverride[] overrides;
			if ( singleOverride != null ) {
				overrides = new AttributeOverride[]{ singleOverride };
			}
			else if ( multipleOverrides != null ) {
				overrides = multipleOverrides.value();
			}
			else {
				overrides = null;
			}

			if ( overrides != null ) {
				Map<String, List<Column>> columnOverrideList = new HashMap<>();

				for ( AttributeOverride depAttr : overrides ) {
					String qualifiedName = StringHelper.qualify( path, depAttr.name() );

					if ( columnOverrideList.containsKey( qualifiedName ) ) {
						columnOverrideList.get( qualifiedName ).add( depAttr.column() );
					}
					else {
						columnOverrideList.put(
							qualifiedName,
							new ArrayList<>( Arrays.asList( depAttr.column() ) )
						);
					}
				}

				for (Map.Entry<String, List<Column>> entry : columnOverrideList.entrySet()) {
					columnOverride.put(
						entry.getKey(),
						entry.getValue().toArray( new Column[entry.getValue().size()] )
					);
				}
			}
		}
		return columnOverride;
	}
