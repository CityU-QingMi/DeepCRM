	public void removeOwner(String name) {
		for ( int i = 0; i < names.length; i++ ) {
			if ( name.equals( names[i] ) ) {

				final String[] newNames = Arrays.copyOf( names, names.length - 1 );
				System.arraycopy( names, i + 1, newNames, i, newNames.length - i);
				names = newNames;

				final CompositeOwner[] newOwners = Arrays.copyOf( owners, owners.length - 1 );
				System.arraycopy( owners, i + 1, newOwners, i, newOwners.length - i);
				owners = newOwners;

				return;
			}
		}
	}
