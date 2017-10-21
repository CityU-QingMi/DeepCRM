	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		final PropertyData that = (PropertyData) o;
		return usingModifiedFlag == that.usingModifiedFlag
				&& store == that.store
				&& EqualsHelper.equals( accessType, that.accessType )
				&& EqualsHelper.equals( beanName, that.beanName )
				&& EqualsHelper.equals( name, that.name )
				&& EqualsHelper.equals( synthetic, that.synthetic );
	}
