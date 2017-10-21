    public void setStream(String fileExtension, InputStreamInterface is) {

        for (int i = 0; i < componentFiles.length; i++) {
            if (componentFiles[i].getName().endsWith(fileExtension)) {
                componentStreams[i] = is;

                break;
            }
        }
    }
