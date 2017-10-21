	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		List<Enum> result = new ArrayList<Enum>();
		for (int a = 0; a < values.length; a++) {
			Enum e = Enum.valueOf(OperationsEnum.class, values[a]);
			if (e != null)
				result.add(e);
		}
		return result;
	}
