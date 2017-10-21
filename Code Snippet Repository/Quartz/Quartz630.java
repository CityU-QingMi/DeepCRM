    @Override
    public String getMessage() {
        if (getValidationExceptions().size() == 0) { return super.getMessage(); }

        StringBuffer sb = new StringBuffer();

        boolean first = true;

        for (Iterator<Exception> iter = getValidationExceptions().iterator(); iter
                .hasNext(); ) {
            Exception e = iter.next();

            if (!first) {
                sb.append('\n');
                first = false;
            }

            sb.append(e.getMessage());
        }

        return sb.toString();
    }
