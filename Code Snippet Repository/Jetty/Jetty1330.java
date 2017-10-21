    private void parsedTrailer()
    {
        // handler last header if any.  Delayed to here just in case there was a continuation line (above)
        if (_headerString!=null || _valueString!=null)
            _handler.parsedTrailer(_field!=null?_field:new HttpField(_header,_headerString,_valueString));

        _headerString=_valueString=null;
        _header=null;
        _value=null;
        _field=null;
    }
