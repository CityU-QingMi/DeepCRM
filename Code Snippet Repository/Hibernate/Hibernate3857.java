	public void setName(String name) {
		if (
				StringHelper.isNotEmpty( name ) &&
						Dialect.QUOTE.indexOf( name.charAt( 0 ) ) > -1 //TODO: deprecated, remove eventually
				) {
			quoted = true;
			this.name = name.substring( 1, name.length() - 1 );
		}
		else {
			this.name = name;
		}
	}
