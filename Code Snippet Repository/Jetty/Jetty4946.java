        protected void write (int b)
        throws IOException
        {
            if (MultiPartInputStreamParser.this._config.getMaxFileSize() > 0 && _size + 1 > MultiPartInputStreamParser.this._config.getMaxFileSize())
                throw new IllegalStateException ("Multipart Mime part "+_name+" exceeds max filesize");

            if (MultiPartInputStreamParser.this._config.getFileSizeThreshold() > 0 && _size + 1 > MultiPartInputStreamParser.this._config.getFileSizeThreshold() && _file==null)
                createFile();

            _out.write(b);
            _size ++;
        }
