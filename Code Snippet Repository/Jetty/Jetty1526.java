        public String getContent()
        {
            if (_content==null)
                return null;
            byte[] bytes=_content.toByteArray();

            String content_type=get(HttpHeader.CONTENT_TYPE);
            String encoding=MimeTypes.getCharsetFromContentType(content_type);
            Charset charset=encoding==null?StandardCharsets.UTF_8:Charset.forName(encoding);

            return new String(bytes,charset);
        }
