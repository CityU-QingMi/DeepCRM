	@Override
	public void writeExternal(ObjectOutput oos) throws IOException {
		oos.writeBoolean( sorted );

		oos.writeInt( executables.size() );
		for ( E e : executables ) {
			oos.writeObject( e );
		}

		// if the spaces are initialized, write them out for usage after deserialization
		if ( querySpaces == null ) {
			oos.writeInt( -1 );
		}
		else {
			oos.writeInt( querySpaces.size() );
			// these are always String, why we treat them as Serializable instead is beyond me...
			for ( Serializable querySpace : querySpaces ) {
				oos.writeUTF( querySpace.toString() );
			}
		}
	}
