        private JettyDataStream(ByteBuffer content, AsyncContext async, ServletOutputStream out)
        {
            // Make a readonly copy of the passed buffer. This uses the same underlying content
            // without a copy, but gives this instance its own position and limit.
            this.content = content.asReadOnlyBuffer();
            // remember the ultimate limit.
            this.limit=this.content.limit();
            this.async = async;
            this.out = (HttpOutput)out;
        }
