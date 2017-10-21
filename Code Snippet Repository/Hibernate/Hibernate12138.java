	public String staticImport(String fqcn, String member) {
		String local = fqcn + "." + member;
		imports.add( local );
		staticImports.add( local );

		if ( member.equals( "*" ) ) {
			return "";
		}
		else {
			return member;
		}
	}
