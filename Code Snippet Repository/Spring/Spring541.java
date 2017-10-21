	protected void appendHintMessage(StringBuilder msg) {
		msg.append("Did you mean ");
		for (int i = 0; i < this.possibleMatches.length; i++) {
			msg.append('\'');
			msg.append(this.possibleMatches[i]);
			if (i < this.possibleMatches.length - 2) {
				msg.append("', ");
			}
			else if (i == this.possibleMatches.length - 2) {
				msg.append("', or ");
			}
		}
		msg.append("'?");
	}
