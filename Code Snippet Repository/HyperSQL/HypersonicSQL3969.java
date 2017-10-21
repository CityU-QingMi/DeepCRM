    String formatIdentifier(String id) {

        if (id == null) {
            return id;
        }

        if (id.equals("")) {
            return id;
        }

        if (!id.toUpperCase().equals(id)) {
            return (quote + id + quote);
        }

        if (!Character.isLetter(id.charAt(0)) || (id.indexOf(' ') != -1)) {
            return (quote + id + quote);
        }

        return id;
    }
