	@Override
    public Object convertValue(Map<String, Object> context, Object value, Class toType) {
		if(value instanceof String) {
			return decodePerson((String)value);
		} else if(value instanceof String && value.getClass().isArray()) {
			return decodePerson(((String[])value)[0]);
		} else {
			Person person = (Person)value;
			return person.getFirstName() + ":" + person.getLastName();
		}
	}
