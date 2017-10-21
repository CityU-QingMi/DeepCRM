	public static Binding doBind(Binder binder, File file, Origin origin) {
		final FileInputStream fis;
		try {
			fis = new FileInputStream( file );
		}
		catch ( FileNotFoundException e ) {
			throw new MappingNotFoundException( e, origin );
		}
		return InputStreamXmlSource.doBind( binder, fis, origin, true );
	}
