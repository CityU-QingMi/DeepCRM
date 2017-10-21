        public InputStream getInputStream() throws IOException
        {
           if (_file != null)
           {
               //written to a file, whether temporary or not
               return new BufferedInputStream (new FileInputStream(_file));
           }
           else
           {
               //part content is in memory
               return new ByteArrayInputStream(_bout.getBuf(),0,_bout.size());
           }
        }
