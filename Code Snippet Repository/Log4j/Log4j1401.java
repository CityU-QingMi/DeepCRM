    @Override
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        StringMapMessage msg;
        if (event.getMessage() instanceof StringMapMessage) {
            msg = (StringMapMessage) event.getMessage();
        } else {
            return;
        }
        final IndexedReadOnlyStringMap sortedMap = msg.getIndexedReadOnlyStringMap();
        // if there is no additional options, we output every single
        // Key/Value pair for the Map in a similar format to Hashtable.toString()
        if (key == null) {
            if (sortedMap.isEmpty()) {
                toAppendTo.append("{}");
                return;
            }
            toAppendTo.append("{");
            for (int i = 0; i < sortedMap.size(); i++) {
                if (i > 0) {
                    toAppendTo.append(", ");
                }
                toAppendTo.append(sortedMap.getKeyAt(i)).append('=').append((String)sortedMap.getValueAt(i));
            }
            toAppendTo.append('}');
        } else {
            // otherwise they just want a single key output
            final String val = sortedMap.getValue(key);

            if (val != null) {
                toAppendTo.append(val);
            }
        }
    }
