    protected String readFile(File file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String s;
            StringBuilder buffer = new StringBuilder();

            while ((s = in.readLine()) != null) {
                buffer.append(s).append('\n');
            }

            return buffer.toString();
        } catch (FileNotFoundException e) {
            if (LOG.isWarnEnabled()) {
        	LOG.warn("File not found");
            }
        } catch (IOException e) {
            LOG.error("Cannot read file: "+file, e);
        }

        return null;
    }
