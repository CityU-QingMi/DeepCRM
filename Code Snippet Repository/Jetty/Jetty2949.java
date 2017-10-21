    protected void recycle()
    {
        _status = HttpStatus.OK_200;
        _reason = null;
        _locale = null;
        _mimeType = null;
        _characterEncoding = null;
        _contentType = null;
        _outputType = OutputType.NONE;
        _contentLength = -1;
        _out.recycle();
        _fields.clear();
        _encodingFrom=EncodingFrom.NOT_SET;
    }
