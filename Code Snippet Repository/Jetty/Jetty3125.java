    @Override
    public void write(ByteBuffer content, boolean complete, Callback callback)
    {
        switch (_state.get())
        {
            case MIGHT_COMPRESS:
                commit(content,complete,callback);
                break;

            case NOT_COMPRESSING:
                _interceptor.write(content, complete, callback);
                return;

            case COMMITTING:
                callback.failed(new WritePendingException());
                break;

            case COMPRESSING:
                gzip(content,complete,callback);
                break;

            default:
                callback.failed(new IllegalStateException("state="+_state.get()));
                break;
        }
    }
