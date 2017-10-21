	private String getWholeAssociationPath(CriteriaImpl.Subcriteria subcriteria) {
		String path = subcriteria.getPath();

		// some messy, complex stuff here, since createCriteria() can take an
		// aliased path, or a path rooted at the creating criteria instance
		Criteria parent = null;
		if ( path.indexOf( '.' ) > 0 ) {
			// if it is a compound path
			String testAlias = StringHelper.root( path );
			if ( !testAlias.equals( subcriteria.getAlias() ) ) {
				// and the qualifier is not the alias of this criteria
				//      -> check to see if we belong to some criteria other
				//          than the one that created us
				parent = aliasCriteriaMap.get( testAlias );
			}
		}
		if ( parent == null ) {
			// otherwise assume the parent is the the criteria that created us
			parent = subcriteria.getParent();
		}
		else {
			path = StringHelper.unroot( path );
		}

		if ( parent.equals( rootCriteria ) ) {
			// if its the root criteria, we are done
			return path;
		}
		else {
			// otherwise, recurse
			return getWholeAssociationPath( (CriteriaImpl.Subcriteria) parent ) + '.' + path;
		}
	}
