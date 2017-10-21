    private String paramsToString(Map<String, String[]> map) {

        if (map == null) {
            return null;
        }

        StringBuilder string = new StringBuilder();

        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            if(entry.getKey() != null) {
                string.append(",").append(entry.getKey()).append("=").append(entry.getValue()[0]);
            }
        }

        return Utilities.toBase64(string.toString().substring(1).getBytes());
    }
