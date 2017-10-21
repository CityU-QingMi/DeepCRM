        @Override
        public void actionPerformed(final ActionEvent e) {
            final String encoding = System.getProperty("file.encoding");
            try {
                contextAdmin.setConfigText(configTextArea.getText(), encoding);
                populateWidgets();
                showConfirmation();
            } catch (final Exception ex) {
                populateWidgets();
                handle("Could not reconfigure from XML", ex);
            }
        }
