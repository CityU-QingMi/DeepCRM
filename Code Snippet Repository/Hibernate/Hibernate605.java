	@Override
	public void handleEntry(ArchiveEntry entry, ArchiveContext context) {
		// Ultimately we'd like to leverage Jandex here as long term we want to move to
		// using Jandex for annotation processing.  But even then, Jandex atm does not have
		// any facility for passing a stream and conditionally indexing it into an Index or
		// returning existing ClassInfo objects.
		//
		// So not sure we can ever not do this unconditional input stream read :(
		final ClassFile classFile = toClassFile( entry );
		final ClassDescriptor classDescriptor = toClassDescriptor( classFile, entry );

		if ( classDescriptor.getCategorization() == ClassDescriptor.Categorization.OTHER ) {
			return;
		}

		resultCollector.handleClass( classDescriptor, context.isRootUrl() );
	}
