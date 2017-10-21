    private Collection<Part> getParts(MultiMap<String> params) throws IOException, ServletException
    {
        if (_multiPartInputStream == null)
            _multiPartInputStream = (MultiPartInputStreamParser)getAttribute(__MULTIPART_INPUT_STREAM);

        if (_multiPartInputStream == null)
        {
            MultipartConfigElement config = (MultipartConfigElement)getAttribute(__MULTIPART_CONFIG_ELEMENT);

            if (config == null)
                throw new IllegalStateException("No multipart config for servlet");

            _multiPartInputStream = new MultiPartInputStreamParser(getInputStream(),
                                                             getContentType(), config,
                                                             (_context != null?(File)_context.getAttribute("javax.servlet.context.tempdir"):null));

            setAttribute(__MULTIPART_INPUT_STREAM, _multiPartInputStream);
            setAttribute(__MULTIPART_CONTEXT, _context);
            Collection<Part> parts = _multiPartInputStream.getParts(); //causes parsing
            ByteArrayOutputStream os = null;
            for (Part p:parts)
            {
                MultiPartInputStreamParser.MultiPart mp = (MultiPartInputStreamParser.MultiPart)p;
                if (mp.getContentDispositionFilename() == null)
                {
                    // Servlet Spec 3.0 pg 23, parts without filename must be put into params.
                    String charset = null;
                    if (mp.getContentType() != null)
                        charset = MimeTypes.getCharsetFromContentType(mp.getContentType());

                    try (InputStream is = mp.getInputStream())
                    {
                        if (os == null)
                            os = new ByteArrayOutputStream();
                        IO.copy(is, os);
                        String content=new String(os.toByteArray(),charset==null?StandardCharsets.UTF_8:Charset.forName(charset));
                        if (_contentParameters == null)
                            _contentParameters = params == null ? new MultiMap<>() : params;
                        _contentParameters.add(mp.getName(), content);
                    }
                    os.reset();
                }
            }
        }

        return _multiPartInputStream.getParts();
    }
