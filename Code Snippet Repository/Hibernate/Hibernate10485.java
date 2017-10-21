	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !( o instanceof AdvancedEntity ) ) {
			return false;
		}

		AdvancedEntity that = (AdvancedEntity) o;

		if ( dynamicConfiguration != null ? !dynamicConfiguration.equals( that.dynamicConfiguration ) : that.dynamicConfiguration != null ) {
			return false;
		}
		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		if ( note != null ? !note.equals( that.note ) : that.note != null ) {
			return false;
		}

		return true;
	}
