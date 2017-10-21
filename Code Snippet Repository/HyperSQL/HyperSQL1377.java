        public TarEntrySupplicant(String path, InputStreamInterface is,
                                  TarFileOutputStream tarStream,
                                  long paxThreshold)
                throws FileNotFoundException, TarMalformatException {

            // Must use an expression-embedded ternary here to satisfy compiler
            // that this() call be first statement in constructor.
            this(path, '0', tarStream, paxThreshold);

            // Difficult call for '0'.  binary 0 and character '0' both mean
            // regular file.  Binary 0 pre-UStar is probably more portable,
            // but we are writing a valid UStar header, and I doubt anybody's
            // tar implementation would choke on this since there is no
            // outcry of UStar archives failing to work with older tars.
            modTime     = System.currentTimeMillis() / 1000L;
            fileMode    = DEFAULT_FILE_MODES;
            inputStream = is;
        }
