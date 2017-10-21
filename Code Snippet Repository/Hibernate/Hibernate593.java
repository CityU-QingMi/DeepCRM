	public JarProtocolArchiveDescriptor(
			ArchiveDescriptorFactory archiveDescriptorFactory,
			URL url,
			String incomingEntry) {
		if ( incomingEntry != null && incomingEntry.length() > 0 ) {
			throw new IllegalArgumentException( "jar:jar: not supported: " + url );
		}

		final String urlFile = url.getFile();
		final int subEntryIndex = urlFile.lastIndexOf( "!" );
		if ( subEntryIndex == -1 ) {
			throw new AssertionFailure( "JAR URL does not contain '!/' :" + url );
		}

		final String subEntry;
		if ( subEntryIndex + 1 >= urlFile.length() ) {
			subEntry = "";
		}
		else {
			subEntry = urlFile.substring( subEntryIndex + 1 );
		}

		final URL fileUrl = archiveDescriptorFactory.getJarURLFromURLEntry( url, subEntry );
		delegateDescriptor = archiveDescriptorFactory.buildArchiveDescriptor( fileUrl, subEntry );
	}
