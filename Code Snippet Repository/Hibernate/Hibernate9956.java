	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (beanName != null ? beanName.hashCode() : 0);
		result = 31 * result + (accessType != null ? accessType.hashCode() : 0);
		result = 31 * result + (store != null ? store.hashCode() : 0);
		result = 31 * result + (usingModifiedFlag ? 1 : 0);
		result = 31 * result + (synthetic ? 1 : 0);
		return result;
	}
