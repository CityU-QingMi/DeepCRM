    public synchronized InputStream getResourceStream(String name)
            throws ResourceNotFoundException {
        if ((name == null) || (name.length() == 0)) {
            throw new ResourceNotFoundException("No template name provided");
        }

        if (name.startsWith("/")) {
            name = name.substring(1);
        }

        try {
            return DefaultBundleAccessor.getInstance().loadResourceAsStream(name);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
