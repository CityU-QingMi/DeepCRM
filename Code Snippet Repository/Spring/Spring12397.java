	private void renderOption(TagWriter tagWriter, Object item, Object value, Object label) throws JspException {
		tagWriter.startTag("option");
		writeCommonAttributes(tagWriter);

		String valueDisplayString = getDisplayString(value);
		String labelDisplayString = getDisplayString(label);

		valueDisplayString = processOptionValue(valueDisplayString);

		// allows render values to handle some strange browser compat issues.
		tagWriter.writeAttribute("value", valueDisplayString);

		if (isOptionSelected(value) || (value != item && isOptionSelected(item))) {
			tagWriter.writeAttribute("selected", "selected");
		}
		if (isOptionDisabled()) {
			tagWriter.writeAttribute("disabled", "disabled");
		}
		tagWriter.appendValue(labelDisplayString);
		tagWriter.endTag();
	}
