        protected void open()
        throws IOException
        {
            //We will either be writing to a file, if it has a filename on the content-disposition
            //and otherwise a byte-array-input-stream, OR if we exceed the getFileSizeThreshold, we
            //will need to change to write to a file.
            if (isWriteFilesWithFilenames() && _filename != null && _filename.trim().length() > 0)
            {
                createFile();
            }
            else
            {
                //Write to a buffer in memory until we discover we've exceed the
                //MultipartConfig fileSizeThreshold
                _out = _bout= new ByteArrayOutputStream2();
            }
        }
