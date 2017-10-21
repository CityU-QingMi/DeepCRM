        protected TarEntrySupplicant(String path, char typeFlag,
                                     TarFileOutputStream tarStream,
                                     long paxThreshold)
                throws TarMalformatException {
            this.paxThreshold = paxThreshold;

            if (path == null) {
                throw new IllegalArgumentException(
                    RB.missing_supp_path.getString());
            }

            this.path      = (swapOutDelim == null)
                             ? path
                             : path.replace(swapOutDelim.charValue(), '/');
            this.tarStream = tarStream;
            writeField(TarHeaderField.typeflag, typeFlag);

            if ((typeFlag == '\0') || (typeFlag == ' ')) {
                writeField(TarHeaderField.uname,
                           System.getProperty("user.name"), HEADER_TEMPLATE);
                writeField(TarHeaderField.gname, "root", HEADER_TEMPLATE);

                // Setting UNAME and GNAME at the instance level instead of the
                // static template, because record types 'x' and 'g' do not set
                // these fields.
                // POSIX UStar compliance requires that we set "gname" field.
                // It's impossible for use to determine the correct value from
                // Java.  We punt with "root" because (a) it's the only group
                // name
                // we know should exist on every UNIX system, and (b) every tar
                // client gracefully handles it when extractor user does not
                // have privs for the specified group.
            }
        }
