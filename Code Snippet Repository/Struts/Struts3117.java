        public String getString() throws UnsupportedEncodingException {
            if (isWriterUsed)
                return sw.toString();
            else if (isStreamUsed) {
                if (this.charEncoding != null && !this.charEncoding.equals(""))
                    return bos.toString(charEncoding);
                else
                    return bos.toString("ISO-8859-1");
            } else
                return "";		// target didn't write anything
        }
