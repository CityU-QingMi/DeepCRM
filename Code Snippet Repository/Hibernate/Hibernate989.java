	@Override
	public List<URL> locateResources(String name) {
		final ArrayList<URL> urls = new ArrayList<URL>();
		try {
			final Enumeration<URL> urlEnumeration = getAggregatedClassLoader().getResources( name );
			if ( urlEnumeration != null && urlEnumeration.hasMoreElements() ) {
				while ( urlEnumeration.hasMoreElements() ) {
					urls.add( urlEnumeration.nextElement() );
				}
			}
		}
		catch (Exception ignore) {
		}

		return urls;
	}
