    public void verify(URL url) throws Exception {
        if (url == null) {
            fail("unable to verify a null URL");
        } else if (this.writer == null) {
            fail("AbstractJspWriter.writer not initialized.  Unable to verify");
        }

        StringBuilder buffer = new StringBuilder(128);
        try (InputStream in = url.openStream()) {
	        byte[] buf = new byte[4096];
	        int nbytes;
	
	        while ((nbytes = in.read(buf)) > 0) {
	            buffer.append(new String(buf, 0, nbytes));
	        }
        }

/**/
/**/
/**/
/**/
        String writerString = normalize(writer.toString(), true);
        String bufferString = normalize(buffer.toString(), true);

        assertEquals(bufferString, writerString);
    }
