	public static InheritanceType get(PersistentClass pc) {
		final PersistentClass superclass = pc.getSuperclass();
		if ( superclass == null ) {
			return InheritanceType.NONE;
		}

		// We assume that every subclass is of the same type.
		final Subclass subclass = (Subclass) superclass.getSubclassIterator().next();

		if ( subclass instanceof SingleTableSubclass ) {
			return InheritanceType.SINGLE;
		}
		else if ( subclass instanceof JoinedSubclass ) {
			return InheritanceType.JOINED;
		}
		else if ( subclass instanceof UnionSubclass ) {
			return InheritanceType.TABLE_PER_CLASS;
		}

		throw new MappingException( "Unknown subclass class: " + subclass.getClass() );
	}
