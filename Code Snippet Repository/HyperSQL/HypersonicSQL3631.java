    public ClobInputStream(SessionInterface session, ClobData clob,
                           long offset, long length) {

        final long clobLength = clob.length(session);

        this.session         = session;
        this.clob            = clob;
        this.availableLength = offset + Math.min(length, clobLength - offset);
        this.currentPosition = offset;
        this.streamBlockSize = session.getStreamBlockSize();
    }
