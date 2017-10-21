        public void assertNegotiated(String expectedNegotiation)
        {
            this.ext = (Extension)factory.newInstance(extConfig);

            this.capture = new IncomingFramesCapture();
            this.ext.setNextIncomingFrames(capture);

            this.parser.configureFromExtensions(Collections.singletonList(ext));
            this.parser.setIncomingFramesHandler(ext);
        }
