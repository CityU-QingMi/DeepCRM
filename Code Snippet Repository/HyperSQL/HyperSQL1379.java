        protected long headerChecksum() {
            long sum = 0;

            for (int i = 0; i < rawHeader.length; i++) {
                boolean isInRange =
                    ((i >= TarHeaderField.checksum.getStart())
                     && (i < TarHeaderField.checksum.getStop()));

                sum += isInRange
                       ? 32
                       : (255 & rawHeader[i]);

                // We ignore current contents of the checksum field so that
                // this method will continue to work right, even if we later
                // recycle the header or RE-calculate a header.
            }

            return sum;
        }
