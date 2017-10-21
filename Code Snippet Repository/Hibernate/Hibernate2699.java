	@Override
	public String getDisplayText() {
		StringBuilder buf = new StringBuilder();

		if (getType() == SqlTokenTypes.ALIAS_REF) {
			buf.append("{alias=").append(getOriginalText());
			if (getFromElement() == null) {
				buf.append(", no from element");
			}
			else {
				buf.append(", className=").append(getFromElement().getClassName());
				buf.append(", tableAlias=").append(getFromElement().getTableAlias());
			}
			buf.append("}");
		}
		else {
			buf.append( "{originalText=" ).append( getOriginalText() ).append( "}" );
		}
		return buf.toString();
	}
