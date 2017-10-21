        protected void write (byte[] bytes, int offset, int length)
        throws IOException
        {
            if (MultiPartInputStreamParser.this._config.getMaxFileSize() > 0 && _size + length > MultiPartInputStreamParser.this._config.getMaxFileSize())
                throw new IllegalStateException ("Multipart Mime part "+_name+" exceeds max filesize");

            if (MultiPartInputStreamParser.this._config.getFileSizeThreshold() > 0 && _size + length > MultiPartInputStreamParser.this._config.getFileSizeThreshold() && _file==null)
                createFile();

            _out.write(bytes, offset, length);
            _size += length;
        }
