    protected Label readLabel(int offset, Label[] labels) {
        // SPRING PATCH: leniently handle offset mismatch
        if (offset >= labels.length) {
            return new Label();
        }
        // END OF PATCH
        if (labels[offset] == null) {
            labels[offset] = new Label();
        }
        return labels[offset];
    }
