        @Override
        public boolean check(Object credentials)
        {
            try
            {
                if (credentials instanceof char[])
                    credentials = new String((char[])credentials);
                if (credentials instanceof Password || credentials instanceof String)
                {
                    byte[] digest;
                    synchronized (__md5Lock)
                    {
                        if (__md == null)
                            __md = MessageDigest.getInstance("MD5");
                        __md.reset();
                        __md.update(credentials.toString().getBytes(StandardCharsets.ISO_8859_1));
                        digest = __md.digest();
                    }
                    return byteEquals(_digest, digest);
                }
                else if (credentials instanceof MD5)
                {
                    return equals(credentials);
                }
                else if (credentials instanceof Credential)
                {
                    // Allow credential to attempt check - i.e. this'll work
                    // for DigestAuthModule$Digest credentials
                    return ((Credential)credentials).check(this);
                }
                else
                {
                    LOG.warn("Can't check " + credentials.getClass() + " against MD5");
                    return false;
                }
            }
            catch (Exception e)
            {
                LOG.warn(e);
                return false;
            }
        }
