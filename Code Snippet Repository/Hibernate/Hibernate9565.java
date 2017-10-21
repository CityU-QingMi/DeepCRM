			@Override
			public String toString(LongList value) {
				if ( value == null ) {
					return "null";
				}
				StringBuilder sb = new StringBuilder();
				sb.append( '[' );
				String glue = "";
				for ( Long v : value ) {
					sb.append( glue ).append( v );
					glue = ",";
				}
				sb.append( ']' );
				return sb.toString();
			}
