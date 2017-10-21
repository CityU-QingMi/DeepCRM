    private void formatStructuredElement(final String id, final StructuredDataElement data,
            final StringBuilder sb, final ListChecker checker) {
        if ((id == null && defaultId == null) || data.discard()) {
            return;
        }

        sb.append('[');
        sb.append(id);
        if (!mdcSdId.toString().equals(id)) {
            appendMap(data.getPrefix(), data.getFields(), sb, noopChecker);
        } else {
            appendMap(data.getPrefix(), data.getFields(), sb, checker);
        }
        sb.append(']');
    }
