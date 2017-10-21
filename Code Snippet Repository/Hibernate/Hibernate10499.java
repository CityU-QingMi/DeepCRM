	@Override
	public int hashCode() {
		int result = componentNote != null ? componentNote.hashCode() : 0;
		result = 31 * result + ( manyToManyList != null ? manyToManyList.hashCode() : 0 );
		result = 31 * result + ( oneToOneEntity != null ? oneToOneEntity.hashCode() : 0 );
		result = 31 * result + ( manyToOneEntity != null ? manyToOneEntity.hashCode() : 0 );
		result = 31 * result + ( internalComponent != null ? internalComponent.hashCode() : 0 );
		result = 31 * result + ( internalComponents != null ? internalComponents.hashCode() : 0 );
		return result;
	}
