	private void processZippedRoot(File rootFile, ArchiveContext context) {
		try (final JarFile jarFile = new JarFile(rootFile)){
			final Enumeration<? extends ZipEntry> entries = jarFile.entries();
			while ( entries.hasMoreElements() ) {
				final ZipEntry zipEntry = entries.nextElement();
				if ( zipEntry.isDirectory() ) {
					continue;
				}

				final String name = extractName( zipEntry );
				final String relativeName = extractRelativeName( zipEntry );
				final InputStreamAccess inputStreamAccess;
				try {
					inputStreamAccess = buildByteBasedInputStreamAccess( name, jarFile.getInputStream( zipEntry ) );
				}
				catch (IOException e) {
					throw new ArchiveException(
							String.format(
									"Unable to access stream from jar file [%s] for entry [%s]",
									jarFile.getName(),
									zipEntry.getName()
							)
					);
				}

				final ArchiveEntry entry = new ArchiveEntry() {
					@Override
					public String getName() {
						return name;
					}

					@Override
					public String getNameWithinArchive() {
						return relativeName;
					}

					@Override
					public InputStreamAccess getStreamAccess() {
						return inputStreamAccess;
					}
				};
				context.obtainArchiveEntryHandler( entry ).handleEntry( entry, context );
			}
		}
		catch (IOException e) {
			throw new ArchiveException( "Error accessing jar file [" + rootFile.getAbsolutePath() + "]", e );
		}
	}
