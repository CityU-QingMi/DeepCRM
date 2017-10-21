	@Override
	@SuppressWarnings("")
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		sorted = in.readBoolean();

		final int numberOfExecutables = in.readInt();
		executables.ensureCapacity( numberOfExecutables );
		if ( numberOfExecutables > 0 ) {
			for ( int i = 0; i < numberOfExecutables; i++ ) {
				E e = (E) in.readObject();
				executables.add( e );
			}
		}

		final int numberOfQuerySpaces = in.readInt();
		if ( numberOfQuerySpaces < 0 ) {
			this.querySpaces = null;
		}
		else {
			querySpaces = new HashSet<Serializable>( CollectionHelper.determineProperSizing( numberOfQuerySpaces ) );
			for ( int i = 0; i < numberOfQuerySpaces; i++ ) {
				querySpaces.add( in.readUTF() );
			}
		}
	}
