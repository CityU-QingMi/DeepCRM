	@Override
	@SuppressWarnings("")
	public Binding doBind(Binder binder) {
		if ( strict ) {
			try {
				return new Binding( readSerFile(), getOrigin() );
			}
			catch ( SerializationException e ) {
				throw new MappingException(
						String.format( "Unable to deserialize from cached file [%s]", getOrigin().getName() ) ,
						e,
						getOrigin()
				);
			}
			catch ( FileNotFoundException e ) {
				throw new MappingException(
						String.format( "Unable to locate cached file [%s]", getOrigin().getName() ) ,
						e,
						getOrigin()
				);
			}
		}
		else {
			if ( !isSerfileObsolete() ) {
				try {
					return readSerFile();
				}
				catch ( SerializationException e ) {
					log.unableToDeserializeCache( serFile.getName(), e );
				}
				catch ( FileNotFoundException e ) {
					log.cachedFileNotFound( serFile.getName(), e );
				}
			}
			else {
				log.cachedFileObsolete( serFile );
			}

			log.readingMappingsFromFile( xmlFile.getPath() );
			final Binding binding = FileXmlSource.doBind( binder, xmlFile, getOrigin() );

			writeSerFile( binding );

			return binding;
		}
	}
