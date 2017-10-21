    private String readFromStream(InputStream txtStream, boolean saveStream) {
        String line;
        StringBuilder buf = new StringBuilder();
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader( txtStream, "UTF-8" ) );
            while ((line = in.readLine()) != null) {
                if (line.startsWith("#")) {
                    readComment(line);
                } else {
                    readRule(line);
                }
                
                if (saveStream) {
                    buf.append(line).append("\n");
                }
            }
        } catch (Exception e) {
            mLogger.error(e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e1) {
                mLogger.error(e1);
            }
        }
        return buf.toString();
    }
