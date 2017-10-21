    private String getViewLocation(String location, String namespace) {
        String view;
        if (!location.startsWith("/")) {
            view = namespace + "/" + location;
        } else {
            view = location;
        }

        if (view.indexOf('?') != -1) {
            view = view.substring(0, view.indexOf('?'));
        }

        return view;
    }
