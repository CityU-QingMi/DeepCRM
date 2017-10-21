	private boolean isEnhanced(String owner, String name, String desc) {
		for ( FieldDescription enhancedField : enhancedFields ) {
			if ( enhancedField.getName().equals( name )
					&& enhancedField.getDescriptor().equals( desc )
					&& enhancedField.getDeclaringType().asErasure().getInternalName().equals( owner ) ) {
				return true;
			}
		}
		return false;
	}
