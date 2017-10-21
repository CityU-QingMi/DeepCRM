        public GZIPContentTransformer(ContentTransformer transformer)
        {
            try
            {
                this.transformer = transformer;
                this.out = new ByteArrayOutputStream();
                this.gzipOut = new GZIPOutputStream(out);
            }
            catch (IOException x)
            {
                throw new RuntimeIOException(x);
            }
        }
