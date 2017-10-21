	@Override
	public boolean equals(@Nullable Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj != null && obj instanceof SimpMessageMappingInfo) {
			SimpMessageMappingInfo other = (SimpMessageMappingInfo) obj;
			return (this.destinationConditions.equals(other.destinationConditions) &&
					this.messageTypeMessageCondition.equals(other.messageTypeMessageCondition));
		}
		return false;
	}
