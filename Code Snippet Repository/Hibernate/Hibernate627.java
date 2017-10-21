	public void apply(MetadataSources metadataSources) {
		switch ( getType() ) {
			case RESOURCE: {
				metadataSources.addResource( getReference() );
				break;
			}
			case CLASS: {
				metadataSources.addAnnotatedClassName( getReference() );
				break;
			}
			case FILE: {
				metadataSources.addFile( getReference() );
				break;
			}
			case PACKAGE: {
				metadataSources.addPackage( getReference() );
				break;
			}
			case JAR: {
				metadataSources.addJar( new File( getReference() ) );
				break;
			}
		}
	}
