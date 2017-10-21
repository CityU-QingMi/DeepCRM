    public static void setFontSize(String inFontSize) {

        // weconsultants@users 20050215 - Changed for Compatbilty fix for JDK 1.3
        // Convert Strng to float for deriveFont() call
        Float stageFloat = new Float(inFontSize);
        float fontSize   = stageFloat.floatValue();
        Font  fonttTree  = fOwner.tTree.getFont().deriveFont(fontSize);

        fOwner.tTree.setFont(fonttTree);

        Font fontTxtCommand =
            fOwner.txtCommand.getFont().deriveFont(fontSize);

        fOwner.txtCommand.setFont(fontTxtCommand);

        Font fontTxtResult = fOwner.txtResult.getFont().deriveFont(fontSize);

        fOwner.txtResult.setFont(fontTxtResult);
    }
