    public String execute() {
        if (value == null) {
            return ERROR;
        } else {
            values = new ArrayList();

            if (separator != null) {
                StringTokenizer tokens = new StringTokenizer(value.toString(), separator);

                while (tokens.hasMoreTokens()) {
                    String token = tokens.nextToken().trim();
                    if (converter != null) {
                        try {
                            Object convertedObj = converter.convert(token);
                            values.add(convertedObj);
                        }
                        catch(Exception e) { // make sure things, goes on, we just ignore the bad ones
                            LOG.warn("Unable to convert [{}], skipping this token, it will not appear in the generated iterator", token, e);
                        }
                    }
                    else {
                        values.add(token);
                    }
                }
            } else {
                values.add(value.toString());
            }

            // Count default is the size of the list of values
            if (count == 0) {
                count = values.size();
            }

            return SUCCESS;
        }
    }
