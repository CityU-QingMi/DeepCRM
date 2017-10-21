	public boolean equals(Object other) {
		if (other == this) {
            return true;
        }
		if (!(other instanceof CustomTemplateRendition)) {
            return false;
        }
		CustomTemplateRendition o = (CustomTemplateRendition) other;
		return new EqualsBuilder().append(getWeblogTemplate().getId(), o.getWeblogTemplate().getId())
				.append(getTemplate(), o.getTemplate()).isEquals();
	}
