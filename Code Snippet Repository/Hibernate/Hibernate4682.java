	public QuerySelect copy() {
		QuerySelect copy = new QuerySelect( dialect );
		copy.joins = this.joins.copy();
		copy.select.append( this.select.toString() );
		copy.where.append( this.where.toString() );
		copy.groupBy.append( this.groupBy.toString() );
		copy.orderBy.append( this.orderBy.toString() );
		copy.having.append( this.having.toString() );
		copy.comment = this.comment;
		copy.distinct = this.distinct;
		return copy;
	}
