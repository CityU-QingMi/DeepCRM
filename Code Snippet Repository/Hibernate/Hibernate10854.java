	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();

		output.append( "VersionsJoinTableRangeComponentTestEntity {" );
		output.append( " id = \"" ).append( this.getId() ).append( "\"," );
		output.append( " component1 = \"" ).append( this.component1 )
				.append( "\", " );
		output.append( " component2 = \"" ).append( this.component2 ).append( "\"}" );
		output.append( " component3 = \"" ).append( this.component3 ).append( "\"}" );
		return output.toString();
	}
