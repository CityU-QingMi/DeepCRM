	@Override
	public void add(String name) {
		if ( suspended ) {
			return;
		}
		if ( !contains( name ) ) {
			names = Arrays.copyOf( names, names.length + 1 );
			names[names.length - 1] = name;
		}
	}
