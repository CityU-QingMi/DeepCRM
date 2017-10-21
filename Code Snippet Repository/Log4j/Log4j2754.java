    private void populateWidgets() {
        try {
            configTextArea.setText(contextAdmin.getConfigText());
        } catch (final Exception ex) {
            final StringWriter sw = new StringWriter(ERR_MSG_INITIAL_BUFFER_SIZE);
            ex.printStackTrace(new PrintWriter(sw));
            configTextArea.setText(sw.toString());
        }
        final String uri = contextAdmin.getConfigLocationUri();
        locationTextField.setText(uri);
    }
