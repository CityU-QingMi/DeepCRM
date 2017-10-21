	@Override
	public int hashCode() {
		int hashCode = (this.from == null ? 0 : this.from.hashCode());
		hashCode = 29 * hashCode + (this.replyTo == null ? 0 : this.replyTo.hashCode());
		for (int i = 0; this.to != null && i < this.to.length; i++) {
			hashCode = 29 * hashCode + (this.to == null ? 0 : this.to[i].hashCode());
		}
		for (int i = 0; this.cc != null && i < this.cc.length; i++) {
			hashCode = 29 * hashCode + (this.cc == null ? 0 : this.cc[i].hashCode());
		}
		for (int i = 0; this.bcc != null && i < this.bcc.length; i++) {
			hashCode = 29 * hashCode + (this.bcc == null ? 0 : this.bcc[i].hashCode());
		}
		hashCode = 29 * hashCode + (this.sentDate == null ? 0 : this.sentDate.hashCode());
		hashCode = 29 * hashCode + (this.subject == null ? 0 : this.subject.hashCode());
		hashCode = 29 * hashCode + (this.text == null ? 0 : this.text.hashCode());
		return hashCode;
	}
