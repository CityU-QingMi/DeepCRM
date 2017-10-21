	@Override
	public void add(String name, int size) {
		for ( int i = 0; i < names.length; i++ ) {
			if ( names[i].equals( name ) ) {
				sizes[i] = size;
				return;
			}
		}
		names = Arrays.copyOf( names, names.length + 1 );
		names[names.length - 1] = name;
		sizes = Arrays.copyOf( sizes, sizes.length + 1 );
		sizes[sizes.length - 1] = size;
	}
