    @Override
    public void formatTo(final StringBuilder buffer) {
        if (formattedMessage != null) {
            buffer.append(formattedMessage);
        } else {
            if (indices[0] < 0) {
                ParameterFormatter.formatMessage(buffer, messagePattern, argArray, usedCount);
            } else {
                ParameterFormatter.formatMessage2(buffer, messagePattern, argArray, usedCount, indices);
            }
        }
    }
