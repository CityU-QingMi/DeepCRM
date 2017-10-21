    public static void setFont() {

        Font txtResultFont = fOwner.txtResult.getFont();

        fOwner.txtResult.setFont(
            new Font(
                fontsComboBox.getSelectedItem().toString(),
                txtResultFont.getStyle(), txtResultFont.getSize()));

        Font txtCommandFont = fOwner.txtResult.getFont();

        fOwner.txtCommand.setFont(
            new Font(
                fontsComboBox.getSelectedItem().toString(),
                txtCommandFont.getStyle(), txtCommandFont.getSize()));

        Font txtTreeFont = fOwner.txtResult.getFont();

        fOwner.tTree.setFont(
            new Font(
                fontsComboBox.getSelectedItem().toString(),
                txtTreeFont.getStyle(), txtTreeFont.getSize()));
    }
