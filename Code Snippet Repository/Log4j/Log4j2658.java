    @Override
    public void setBody(final byte[] body) {
        if (body == null || body.length == 0) {
            super.setBody(new byte[0]);
            return;
        }
        if (compress) {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (GZIPOutputStream os = new GZIPOutputStream(baos)) {
                os.write(body);
            } catch (final IOException ioe) {
                throw new LoggingException("Unable to compress message", ioe);
            }
            super.setBody(baos.toByteArray());
        } else {
            super.setBody(body);
        }
    }
