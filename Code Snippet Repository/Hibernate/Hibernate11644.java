	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(getClass().getName());
		sb.append("[id=");
		sb.append(id);
		sb.append(",branch=");
		sb.append(branch);
		sb.append(",balance=");
		sb.append(balance);
		sb.append(",accountHolder=");
		sb.append(accountHolder);
		sb.append("]");
		return sb.toString();
	}
