	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( !super.equals( obj ) ) {
			return false;
		}
		if ( getClass() != obj.getClass() ) {
			return false;
		}
		EmbeddableWithDeclaredData other = (EmbeddableWithDeclaredData) obj;
		if ( codeArt == null ) {
			if ( other.codeArt != null ) {
				return false;
			}
		}
		else if ( !codeArt.equals( other.codeArt ) ) {
			return false;
		}
		return true;
	}
