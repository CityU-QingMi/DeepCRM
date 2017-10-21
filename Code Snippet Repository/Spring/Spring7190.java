	@Override
	@Nullable
	public SimpMessageMappingInfo getMatchingCondition(Message<?> message) {
		SimpMessageTypeMessageCondition typeCond = this.messageTypeMessageCondition.getMatchingCondition(message);
		if (typeCond == null) {
			return null;
		}
		DestinationPatternsMessageCondition destCond = this.destinationConditions.getMatchingCondition(message);
		if (destCond == null) {
			return null;
		}
		return new SimpMessageMappingInfo(typeCond, destCond);
	}
