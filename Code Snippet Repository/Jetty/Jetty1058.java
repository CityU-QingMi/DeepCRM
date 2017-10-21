    @Override
    public String toString()
    {
        return String.format("%s@%x{l:%s <-> r:%s,queueSize=%d,sendWindow=%s,recvWindow=%s,streams=%d,%s}",
                getClass().getSimpleName(),
                hashCode(),
                getEndPoint().getLocalAddress(),
                getEndPoint().getRemoteAddress(),
                flusher.getQueueSize(),
                sendWindow,
                recvWindow,
                streams.size(),
                closed);
    }
