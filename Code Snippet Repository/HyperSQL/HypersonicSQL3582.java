    public BlobInputStream(SessionInterface session, BlobData blob,
                           long offset, long length) {

        final long blobLength = blob.length(session);

        this.session         = session;
        this.blob            = blob;
        this.availableLength = offset + Math.min(length, blobLength - offset);
        this.currentPosition = offset;
        this.streamBlockSize = session.getStreamBlockSize();
    }
