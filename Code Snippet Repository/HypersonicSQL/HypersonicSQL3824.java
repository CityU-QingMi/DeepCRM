    private void setNotNull() {

        isNullable = true;

        for (int i = 0; i < constraints.length; i++) {
            if (constraints[i].isNotNull()) {
                isNullable = false;
            }
        }
    }
