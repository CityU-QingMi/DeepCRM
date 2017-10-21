    String formatName(String t) {

        String Name = "";

        if ((sSchema != null) && (sSchema.length() > 0)) {
            Name = sSchema + ".";
        }

        Name += formatIdentifier(t);

        return Name;
    }
