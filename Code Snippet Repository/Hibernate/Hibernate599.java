	@SuppressWarnings("")
	protected boolean isListedOrDetectable(String name, boolean rootUrl) {
		// IMPL NOTE : protect the calls to getExplicitlyListedClassNames unless needed,
		// since it can take time with lots of listed classes.
		if ( rootUrl ) {
			// The entry comes from the root url.  Allow it if either:
			//		1) we are allowed to discover classes/packages in the root url
			//		2) the entry was explicitly listed
			return options.canDetectUnlistedClassesInRoot()
					|| environment.getExplicitlyListedClassNames().contains( name );
		}
		else {
			// The entry comes from a non-root url.  Allow it if either:
			//		1) we are allowed to discover classes/packages in non-root urls
			//		2) the entry was explicitly listed
			return options.canDetectUnlistedClassesInNonRoot()
					|| environment.getExplicitlyListedClassNames().contains( name );
		}
	}
