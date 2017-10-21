    private void buildLoggers(final String prefix) {
        final int preLength = prefix.length();
        for (final Map.Entry<Object, Object> entry : properties.entrySet()) {
            final Object keyObj = entry.getKey();
            if (keyObj != null) {
                final String key = keyObj.toString();
                if (key.startsWith(prefix)) {
                    final String name = key.substring(preLength);
                    final Object value = entry.getValue();
                    if (value != null) {
                        // a Level may be followed by a list of Appender refs.
                        final String valueStr = value.toString();
                        final String[] split = valueStr.split(COMMA_DELIMITED_RE);
                        final String level = getLevelString(split, null);
                        if (level == null) {
                            warn("Level is missing for entry " + entry);
                        } else {
                            final LoggerComponentBuilder newLogger = builder.newLogger(name, level);
                            if (split.length > 1) {
                                // Add Appenders to this logger
                                final String[] sortedAppenderNames = Arrays.copyOfRange(split, 1, split.length);
                                Arrays.sort(sortedAppenderNames);
                                for (final String appenderName : sortedAppenderNames) {
                                    newLogger.add(builder.newAppenderRef(appenderName));
                                }
                            }
                            builder.add(newLogger);
                        }
                    }
                }
            }
        }
    }
