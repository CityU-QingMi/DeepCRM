		@Override
		public ArchiveEntryHandler obtainArchiveEntryHandler(ArchiveEntry entry) {
			final String nameWithinArchive = entry.getNameWithinArchive();

			if ( nameWithinArchive.endsWith( "package-info.class" ) ) {
				return packageEntryHandler;
			}
			else if ( nameWithinArchive.endsWith( ".class" ) ) {
				return classEntryHandler;
			}
			else {
				return fileEntryHandler;
			}
		}
