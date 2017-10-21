	public static <X> List<X> collectionToList(Collection<X> collection) {
		if ( collection instanceof List ) {
			return (List<X>) collection;
		}
		else {
			List<X> list = new ArrayList<>();
			list.addAll( collection );
			return list;
		}
	}
