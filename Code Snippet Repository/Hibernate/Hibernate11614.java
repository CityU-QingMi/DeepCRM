    @Override
    public String toString() {
        String s = getClass().getName();
        s += "[includedClasses=";
        s += listClasses(includedClasses);
        s += ";excludedClasses=";
        s += listClasses(excludedClasses);
        s += ";notFoundClasses=";
        s += listClasses(notFoundClasses);
        s += ";parent=";
        s += getParent();
        s += "]";
        return s;
    }
