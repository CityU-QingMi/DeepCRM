	public WebSphereJtaPlatform() {
		Class tmAccessClass = null;
		WebSphereEnvironment webSphereEnvironment = null;

		for ( WebSphereEnvironment check : WebSphereEnvironment.values() ) {
			try {
				tmAccessClass = Class.forName( check.getTmAccessClassName() );
				webSphereEnvironment = check;
				log.debugf( "WebSphere version : %s", webSphereEnvironment.getWebSphereVersion() );
				break;
			}
			catch ( Exception ignore ) {
				// go on to the next iteration
			}
		}

		if ( webSphereEnvironment == null ) {
			throw new JtaPlatformException( "Could not locate WebSphere TransactionManager access class" );
		}

		this.transactionManagerAccessClass = tmAccessClass;
		this.webSphereEnvironment = webSphereEnvironment;
	}
