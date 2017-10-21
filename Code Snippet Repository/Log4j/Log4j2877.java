    public void set(final String message, final Object p1, final Object p2, final Object p3, final Object p4) {
        final InternalState state = getState();
        state.params[0] = p1;
        state.params[1] = p2;
        state.params[2] = p3;
        state.params[3] = p4;
        state.paramCount = 4;
        int current = 0;
        state.buffer.setLength(0);
        for (int i = 0; i < message.length() - 1; i++) {
            final char c = message.charAt(i);
            if (c == '{' && message.charAt(i + 1) == '}') {
                append(state.params[current++], state.buffer);
                i++;
            } else {
                state.buffer.append(c);
            }
        }
        final char c = message.charAt(message.length() - 1);
        if (c != '}') {
            state.buffer.append(c);
        }
    }
