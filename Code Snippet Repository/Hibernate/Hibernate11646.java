	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(getClass().getName());
		sb.append("[lastName=");
		sb.append(lastName);
		sb.append(",ssn=");
		sb.append(ssn);
		sb.append(",deserialized=");
		sb.append(deserialized);
		sb.append("]");
		return sb.toString();
	}
