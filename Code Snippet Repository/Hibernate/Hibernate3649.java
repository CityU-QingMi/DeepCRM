		private boolean hasAssociation(CompositeType componentType) {
			for ( Type subType : componentType.getSubtypes() ) {
				if ( subType.isEntityType() ) {
					return true;
				}
				else if ( subType.isComponentType() && hasAssociation( ( (CompositeType) subType ) ) ) {
					return true;
				}
			}
			return false;
		}
