        public TarEntrySupplicant(String path, File file,
                                  TarFileOutputStream tarStream,
                                  long paxThreshold)
                throws FileNotFoundException, TarMalformatException {

            // Must use an expression-embedded ternary here to satisfy compiler
            // that this() call be first statement in constructor.
            this(((path == null)
                  ? file.getPath()
                  : path), '0', tarStream, paxThreshold);

            // Difficult call for '0'.  binary 0 and character '0' both mean
            // regular file.  Binary 0 pre-UStar is probably more portable,
            // but we are writing a valid UStar header, and I doubt anybody's
            // tar implementation would choke on this since there is no
            // outcry of UStar archives failing to work with older tars.
            if (!file.isFile()) {
                throw new IllegalArgumentException(
                    RB.nonfile_entry.getString());
            }

            if (!file.canRead()) {
                throw new IllegalArgumentException(
                    RB.read_denied.getString(file.getAbsolutePath()));
            }

            modTime     = file.lastModified() / 1000L;
            fileMode    = TarEntrySupplicant.getLameMode(file);
            dataSize    = file.length();
            inputStream = new InputStreamWrapper(new FileInputStream(file));
        }
