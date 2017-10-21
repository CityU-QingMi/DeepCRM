        public BaseException(final LockFile lockFile, final String inMethod) {

            super();

            if (lockFile == null) {
                throw new NullPointerException("lockFile");
            }

            if (inMethod == null) {
                throw new NullPointerException("inMethod");
            }

            this.lockFile = lockFile;
            this.inMethod = inMethod;
        }
