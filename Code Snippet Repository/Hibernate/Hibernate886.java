	public HibernateTypeSourceImpl(TypeContainer typeContainer) {
		if ( typeContainer.getTypeAttribute() != null ) {
			name = typeContainer.getTypeAttribute();
			parameters = null;
		}
		else if ( typeContainer.getType() != null ) {
			name = typeContainer.getType().getName();
			parameters = Helper.extractParameters( typeContainer.getType().getConfigParameters() );
		}
		else {
			name = null;
			parameters = null;
		}
	}
