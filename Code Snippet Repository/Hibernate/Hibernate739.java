	@Override
	public Binding doBind(Binder binder) {
		final InputStream stream;
		try {
			stream = jarFile.getInputStream( jarFileEntry );
		}
		catch (IOException e) {
			throw new MappingException(
					String.format(
							"Unable to open InputStream for jar file entry [%s : %s]",
							jarFile.getName(),
							jarFileEntry.getName()
					),
					e,
					getOrigin()
			);
		}

		return InputStreamXmlSource.doBind( binder, stream, getOrigin(), true );
	}
