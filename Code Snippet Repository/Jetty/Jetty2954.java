    @Override
    public PrintWriter getWriter() throws IOException
    {
        if (_outputType == OutputType.STREAM)
            throw new IllegalStateException("STREAM");

        if (_outputType == OutputType.NONE)
        {
            /* get encoding from Content-Type header */
            String encoding = _characterEncoding;
            if (encoding == null)
            {
                if (_mimeType!=null && _mimeType.isCharsetAssumed())
                    encoding=_mimeType.getCharsetString();
                else
                {
                    encoding = MimeTypes.getCharsetAssumedFromContentType(_contentType);
                    if (encoding == null)
                    {
                        encoding = MimeTypes.getCharsetInferredFromContentType(_contentType);
                        if (encoding == null)
                            encoding = StringUtil.__ISO_8859_1;
                        setCharacterEncoding(encoding,EncodingFrom.INFERRED);
                    }
                }
            }

            Locale locale = getLocale();

            if (_writer != null && _writer.isFor(locale,encoding))
                _writer.reopen();
            else
            {
                if (StringUtil.__ISO_8859_1.equalsIgnoreCase(encoding))
                    _writer = new ResponseWriter(new Iso88591HttpWriter(_out),locale,encoding);
                else if (StringUtil.__UTF8.equalsIgnoreCase(encoding))
                    _writer = new ResponseWriter(new Utf8HttpWriter(_out),locale,encoding);
                else
                    _writer = new ResponseWriter(new EncodingHttpWriter(_out, encoding),locale,encoding);
            }

            // Set the output type at the end, because setCharacterEncoding() checks for it
            _outputType = OutputType.WRITER;
        }
        return _writer;
    }
