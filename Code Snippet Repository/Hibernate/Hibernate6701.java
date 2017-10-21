	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( o == null || getClass() != o.getClass() ) return false;

		final PaintingPk that = (PaintingPk) o;

		if ( !name.equals( that.getName() ) ) return false;
		if ( !painter.equals( that.getPainter() ) ) return false;

		return true;
	}
