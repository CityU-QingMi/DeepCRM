    @Override
    public void onFrame(Frame frame)
    {
        switch (frame.getType())
        {
            case PREFACE:
                onPreface();
                break;
            case SETTINGS:
                // SPEC: the required reply to this SETTINGS frame is the 101 response.
                onSettings((SettingsFrame)frame, false);
                break;
            case HEADERS:
                onHeaders((HeadersFrame)frame);
                break;
            default:
                super.onFrame(frame);
                break;
        }
    }
