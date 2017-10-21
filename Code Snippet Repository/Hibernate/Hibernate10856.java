	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();

		output.append( "VersionsJoinTableRangeComponentTestEntity {" );
		output.append( " id = \"" ).append( getId() ).append( "\", " );
		output.append( " genericValue = \"" ).append( getGenericValue() ).append(
				"\", "
		);
		output.append( " alternateValue = \"" ).append( this.alternateValue )
				.append( "\"}" );
		return output.toString();
	}
