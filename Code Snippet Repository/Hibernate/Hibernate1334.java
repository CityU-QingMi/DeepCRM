	public XClass getClassWithIdClass(boolean evenIfSubclass) {
		if ( !evenIfSubclass && hasParents() ) {
			return null;
		}
		if ( clazz.isAnnotationPresent( IdClass.class ) ) {
			return clazz;
		}
		else {
			InheritanceState state = InheritanceState.getSuperclassInheritanceState( clazz, inheritanceStatePerClass );
			if ( state != null ) {
				return state.getClassWithIdClass( true );
			}
			else {
				return null;
			}
		}
	}
