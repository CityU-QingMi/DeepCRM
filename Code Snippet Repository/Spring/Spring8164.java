	@Override
	public String toString() {
		return new ToStringCreator(this)
				.append("dataSource", this.dataSource)
				.append("transactionManager", this.transactionManager)
				.append("transactionMode", this.transactionMode)
				.append("encoding", this.encoding)
				.append("separator", this.separator)
				.append("commentPrefix", this.commentPrefix)
				.append("blockCommentStartDelimiter", this.blockCommentStartDelimiter)
				.append("blockCommentEndDelimiter", this.blockCommentEndDelimiter)
				.append("errorMode", this.errorMode)
				.toString();
	}
