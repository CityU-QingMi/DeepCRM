	public boolean equals(Object other) {
		if (other == this) {
            return true;
        }
		if (!(other instanceof SharedThemeTemplateRendition)) {
            return false;
        }
		SharedThemeTemplateRendition o = (SharedThemeTemplateRendition) other;
		return new EqualsBuilder()
				.append(template, o.getTemplate()).isEquals();
	}
