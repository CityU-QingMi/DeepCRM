    public static void setColor(String inTarget) {

        if (inTarget.equals(BACKGROUND)) {
            Color backgroundColor = JColorChooser.showDialog(null,
                "DataBaseManagerSwing Choose Background Color",
                fOwner.txtResult.getBackground());

            if (backgroundColor != null) {
                bgColorButton.setBackground(backgroundColor);
                fOwner.txtCommand.setBackground(backgroundColor);
                fOwner.txtResult.setBackground(backgroundColor);
            }
        } else {
            Color foregroundColor = JColorChooser.showDialog(null,
                "DataBaseManagerSwing Choose Foreground Color",
                fOwner.txtResult.getForeground());

            if (foregroundColor != null) {
                fgColorButton.setBackground(foregroundColor);
                fOwner.txtCommand.setForeground(foregroundColor);
                fOwner.txtResult.setForeground(foregroundColor);
            }
        }
    }
