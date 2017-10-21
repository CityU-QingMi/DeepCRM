    @Override
    public void process(Frame frame, Callback callback)
    {
        notIdle();
        switch (frame.getType())
        {
            case HEADERS:
            {
                onHeaders((HeadersFrame)frame, callback);
                break;
            }
            case DATA:
            {
                onData((DataFrame)frame, callback);
                break;
            }
            case RST_STREAM:
            {
                onReset((ResetFrame)frame, callback);
                break;
            }
            case PUSH_PROMISE:
            {
                onPush((PushPromiseFrame)frame, callback);
                break;
            }
            case WINDOW_UPDATE:
            {
                onWindowUpdate((WindowUpdateFrame)frame, callback);
                break;
            }
            default:
            {
                throw new UnsupportedOperationException();
            }
        }
    }
