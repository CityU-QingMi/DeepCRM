    @Override
    public String toString()
    {
        return String.format("%s@%x[ratio=%.2f,sessionLevel=%s,sessionStallTime=%dms,streamsStallTime=%dms]",
                getClass().getSimpleName(),
                hashCode(),
                bufferRatio,
                sessionLevel,
                getSessionStallTime(),
                getStreamsStallTime());
    }
