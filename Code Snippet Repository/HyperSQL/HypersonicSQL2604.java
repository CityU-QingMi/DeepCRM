        public LockHeldExternallyException(final LockFile lockFile,
                                           final String inMethod,
                                           final long read,
                                           final long heartbeat) {

            super(lockFile, inMethod);

            this.read      = read;
            this.heartbeat = heartbeat;
        }
