	public void add(String name, CompositeOwner owner) {
		for ( int i = 0; i < names.length; i++ ) {
			if ( names[i].equals( name ) ) {
				owners[i] = owner;
				return;
			}
		}
		names = Arrays.copyOf( names, names.length + 1 );
		names[names.length - 1] = name;
		owners = Arrays.copyOf( owners, owners.length + 1 );
		owners[owners.length - 1] = owner;
	}
