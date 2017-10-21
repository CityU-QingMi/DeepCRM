	private void buildHierarchyColumnOverride(XClass element) {
		XClass current = element;
		Map<String, Column[]> columnOverride = new HashMap<String, Column[]>();
		Map<String, JoinColumn[]> joinColumnOverride = new HashMap<String, JoinColumn[]>();
		Map<String, JoinTable> joinTableOverride = new HashMap<String, JoinTable>();
		Map<String, ForeignKey> foreignKeyOverride = new HashMap<String, ForeignKey>();
		while ( current != null && !context.getBuildingOptions().getReflectionManager().toXClass( Object.class ).equals( current ) ) {
			if ( current.isAnnotationPresent( Entity.class ) || current.isAnnotationPresent( MappedSuperclass.class )
					|| current.isAnnotationPresent( Embeddable.class ) ) {
				//FIXME is embeddable override?
				Map<String, Column[]> currentOverride = buildColumnOverride( current, getPath() );
				Map<String, JoinColumn[]> currentJoinOverride = buildJoinColumnOverride( current, getPath() );
				Map<String, JoinTable> currentJoinTableOverride = buildJoinTableOverride( current, getPath() );
				Map<String, ForeignKey> currentForeignKeyOverride = buildForeignKeyOverride( current, getPath() );
				currentOverride.putAll( columnOverride ); //subclasses have precedence over superclasses
				currentJoinOverride.putAll( joinColumnOverride ); //subclasses have precedence over superclasses
				currentJoinTableOverride.putAll( joinTableOverride ); //subclasses have precedence over superclasses
				currentForeignKeyOverride.putAll( foreignKeyOverride ); //subclasses have precedence over superclasses
				columnOverride = currentOverride;
				joinColumnOverride = currentJoinOverride;
				joinTableOverride = currentJoinTableOverride;
				foreignKeyOverride = currentForeignKeyOverride;
			}
			current = current.getSuperclass();
		}

		holderColumnOverride = columnOverride.size() > 0 ? columnOverride : null;
		holderJoinColumnOverride = joinColumnOverride.size() > 0 ? joinColumnOverride : null;
		holderJoinTableOverride = joinTableOverride.size() > 0 ? joinTableOverride : null;
		holderForeignKeyOverride = foreignKeyOverride.size() > 0 ? foreignKeyOverride : null;
	}
