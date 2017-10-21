    private void consume(Content content)
    {
        if (content instanceof EofContent)
        {
            if (content == EARLY_EOF_CONTENT)
                _state = EARLY_EOF;
            else if (_listener == null)
                _state = EOF;
            else
                _state = AEOF;
        }
        
        // Consume the content, either if it was original content
        // or if it was produced by interception
        content.succeeded();
        if (_content==content)
            _content = null;
        else if (_intercepted==content)
            _intercepted = null;
    }
