	@Override
	public boolean needsInserting(Object entry, int i, Type elemType) throws HibernateException {
		final List sn = (List) getSnapshot();
		if ( sn.size() > i && elemType.isSame( sn.get( i ), entry ) ) {
			//a shortcut if its location didn't change!
			return false;
		}
		else {
			//search for it
			//note that this code is incorrect for other than one-to-many
			for ( Object old : sn ) {
				if ( elemType.isSame( old, entry ) ) {
					return false;
				}
			}
			return true;
		}
	}
