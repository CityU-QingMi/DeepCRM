	public CompositeData toCompositeData() {
		try {
			return new CompositeDataSupport(
					COMPOSITE_TYPE,
					ITEM_NAMES,
					new Object[] { roleName, shortName, loadCount, fetchCount, updateCount, removeCount, recreateCount }
			);
		}
		catch (OpenDataException e) {
			throw new RuntimeException( e );
		}
	}
