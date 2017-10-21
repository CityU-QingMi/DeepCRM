        protected void createFile ()
        throws IOException
        {
/**/
/**/
            final boolean USER = true;
            final boolean WORLD = false;
            
            _file = File.createTempFile("MultiPart", "", MultiPartInputStreamParser.this._tmpDir);
            _file.setReadable(false,WORLD); // (reset) disable it for everyone first
            _file.setReadable(true,USER); // enable for user only

            if (_deleteOnExit)
                _file.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(_file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            if (_size > 0 && _out != null)
            {
                //already written some bytes, so need to copy them into the file
                _out.flush();
                _bout.writeTo(bos);
                _out.close();
                _bout = null;
            }
            _out = bos;
        }
