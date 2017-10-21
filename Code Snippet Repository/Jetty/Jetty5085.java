    private void checkCharAppend() throws IOException
    {
        if (_state != UTF8_ACCEPT)
        {
            _appendable.append(REPLACEMENT);
            int state=_state;
            _state=UTF8_ACCEPT;
            throw new NotUtf8Exception("char appended in state "+state);
        }
    }
