	@Override
	public ArchiveDescriptor buildArchiveDescriptor(URL url, String entry) {
		final String protocol = url.getProtocol();
		if ( "jar".equals( protocol ) ) {
			return new JarProtocolArchiveDescriptor( this, url, entry );
		}
		else if ( StringHelper.isEmpty( protocol )
				|| "file".equals( protocol )
				|| "vfszip".equals( protocol )
				|| "vfsfile".equals( protocol ) ) {
			final File file = new File( extractLocalFilePath( url ) );
			if ( file.isDirectory() ) {
				return new ExplodedArchiveDescriptor( this, url, entry );
			}
			else {
				return new JarFileBasedArchiveDescriptor( this, url, entry );
			}
		}
		else {
			//let's assume the url can return the jar as a zip stream
			return new JarInputStreamBasedArchiveDescriptor( this, url, entry );
		}
	}
