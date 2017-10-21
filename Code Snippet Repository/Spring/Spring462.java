	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getClass().getName());
		if (this.wrappedObject != null) {
			sb.append(": wrapping object [").append(ObjectUtils.identityToString(this.wrappedObject)).append("]");
		}
		else {
			sb.append(": no wrapped object set");
		}
		return sb.toString();
	}
