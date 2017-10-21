        @Override
        public void actionPerformed(final ActionEvent e) {
            try {
                contextAdmin.setConfigLocationUri(locationTextField.getText());
                populateWidgets();
                showConfirmation();
            } catch (final Exception ex) {
                populateWidgets();
                handle("Could not reconfigure from location", ex);
            }
        }
