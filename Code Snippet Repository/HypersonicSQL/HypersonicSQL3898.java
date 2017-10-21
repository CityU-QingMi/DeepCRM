    public static void setStyle() {

        int style = Font.PLAIN;

        if (ckbbold.isSelected()) {
            style |= Font.BOLD;
        }

        if (ckbitalic.isSelected()) {
            style |= Font.ITALIC;
        }

        fOwner.tTree.setFont(fOwner.txtCommand.getFont().deriveFont(style));
        fOwner.txtCommand.setFont(
            fOwner.txtCommand.getFont().deriveFont(style));
        fOwner.txtResult.setFont(
            fOwner.txtResult.getFont().deriveFont(style));
    }
