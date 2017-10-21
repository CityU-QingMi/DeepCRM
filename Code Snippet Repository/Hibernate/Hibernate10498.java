	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !( o instanceof PlainComponent ) ) {
			return false;
		}

		PlainComponent that = (PlainComponent) o;

		if ( componentNote != null ? !componentNote.equals( that.componentNote ) : that.componentNote != null ) {
			return false;
		}
		if ( internalComponent != null ? !internalComponent.equals( that.internalComponent ) : that.internalComponent != null ) {
			return false;
		}
		if ( internalComponents != null ? !internalComponents.equals( that.internalComponents ) : that.internalComponents != null ) {
			return false;
		}
		if ( manyToManyList != null ? !manyToManyList.equals( that.manyToManyList ) : that.manyToManyList != null ) {
			return false;
		}
		if ( manyToOneEntity != null ? !manyToOneEntity.equals( that.manyToOneEntity ) : that.manyToOneEntity != null ) {
			return false;
		}
		if ( oneToOneEntity != null ? !oneToOneEntity.equals( that.oneToOneEntity ) : that.oneToOneEntity != null ) {
			return false;
		}

		return true;
	}
