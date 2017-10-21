    protected boolean sendResponse(MetaData.Response info, ByteBuffer content, boolean complete, final Callback callback)
    {
        boolean committing = _committed.compareAndSet(false, true);

        if (LOG.isDebugEnabled())
            LOG.debug("sendResponse info={} content={} complete={} committing={} callback={}",
                    info,
                    BufferUtil.toDetailString(content),
                    complete,
                    committing,
                    callback);

        if (committing)
        {
            // We need an info to commit
            if (info==null)
                info = _response.newResponseMetaData();
            commit(info);

            // wrap callback to process 100 responses
            final int status=info.getStatus();
            final Callback committed = (status<200&&status>=100)?new Commit100Callback(callback):new CommitCallback(callback);

            // committing write
            _transport.send(info, _request.isHead(), content, complete, committed);
        }
        else if (info==null)
        {
            // This is a normal write
            _transport.send(null,_request.isHead(), content, complete, callback);
        }
        else
        {
            callback.failed(new IllegalStateException("committed"));
        }
        return committing;
    }
