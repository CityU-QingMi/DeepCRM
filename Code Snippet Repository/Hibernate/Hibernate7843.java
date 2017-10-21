	public void addCategory(Category category) {
		if ( category == null ) {
			return;
		}

		if ( categories == null ) {
			categories = new HashSet();
		}

		categories.add( category );
		if ( category.getProducts() == null ) {
			category.setProducts( new HashSet() );
		}
		category.getProducts().add( this );
	}
