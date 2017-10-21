    private Object[] getThreadLocalState() {
        Object[] threadLocalState = threadLocal.get();
        if (threadLocalState == null) {
            threadLocalState = new Object[] {
                    charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE)
                            .onUnmappableCharacter(CodingErrorAction.REPLACE),
                    CharBuffer.allocate(charBufferSize),
                    ByteBuffer.allocate(byteBufferSize)
            };
            threadLocal.set(threadLocalState);
        } else {
            ((CharsetEncoder) threadLocalState[0]).reset();
            ((CharBuffer) threadLocalState[1]).clear();
            ((ByteBuffer) threadLocalState[2]).clear();
        }
        return threadLocalState;
    }
