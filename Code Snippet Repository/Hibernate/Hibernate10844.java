	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();

		output.append( "VersionsJoinTableRangeComponent {" );
		output.append( " range = \"" );
		for ( T instance : range ) {
			output.append( instance ).append( "\n" );
		}
		output.append( "\"}" );

		return output.toString();
	}
