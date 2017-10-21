    final void printResource(String key) {

        String          resource;
        StringTokenizer st;

        if (serverBundleHandle < 0) {
            return;
        }

        resource = ResourceBundleHandler.getString(serverBundleHandle, key);

        if (resource == null) {
            return;
        }

        st = new StringTokenizer(resource, "\n\r");

        while (st.hasMoreTokens()) {
            print(st.nextToken());
        }
    }
