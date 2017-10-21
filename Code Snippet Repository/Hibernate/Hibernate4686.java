	public String render() {
		final StringBuilder buf = new StringBuilder( selectValueList.size() * 10 );
		final HashSet<String> uniqueAliases = new HashSet<String>();
		boolean firstExpression = true;
		for ( SelectValue selectValue : selectValueList ) {
			if ( selectValue.alias != null ) {
				if ( ! uniqueAliases.add( selectValue.alias ) ) {
					log.debug( "Skipping select-value with non-unique alias" );
					continue;
				}
			}

			if ( firstExpression ) {
				firstExpression = false;
			}
			else {
				buf.append( ", " );
			}

			if ( selectValue.qualifier != null ) {
				buf.append( selectValue.qualifier ).append( '.' );
			}
			buf.append( selectValue.value );
			if ( selectValue.alias != null ) {
				buf.append( " as " ).append( selectValue.alias );
			}
		}
		return buf.toString();
	}
