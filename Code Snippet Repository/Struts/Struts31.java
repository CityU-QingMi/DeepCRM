	@Override
	public String convertToString(Map context, Object o) {
		List l = (List) o;
		String result = "<";
		for (Iterator i = l.iterator(); i.hasNext(); ) {
			result = result + "[" + i.next() + "]";
		}
		result = result + ">";
		return result;
	}
